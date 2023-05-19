package com;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String options[] = {"1. MY CAMERA","2. RENT A CAMERA","3. VIEW ALL CAMERAS","4. MY WALLET","5. EXIT"};
    public static String subOptions[] = {"1. ADD","2. REMOVE","3. VIEW MY CAMERAS","4. GO TO PREVIOUS MENU"};

    public static void printOptions(String arr[]) {
        for(String str:arr) {
            System.out.println(str);
        }
    }

    public static void run(User user) {
        printOptions(options);
        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("INVALID INPUT");
        }
        switch (choice) {
            case 1:
                printOptions(subOptions);
                int subChoice = 0;
                try {
                    subChoice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("INVALID INPUT");
                }
                switch (subChoice) {
                    case 1:
                        try {
                            System.out.print("ENTER THE CAMERA BRAND - ");
                            String brand = sc.next();
                            System.out.print("ENTER THE MODEL - ");
                            String model = sc.next();
                            System.out.print("ENTER THE PER DAY PRICE (INR) - ");
                            double rent = sc.nextDouble();
                            int cameraId = CameraOperations.cameraList.size()+1;
                            Camera newCamera = new Camera();
                            newCamera.setCameraId(cameraId);
                            newCamera.setBrand(brand);
                            newCamera.setModel(model);
                            newCamera.setRent(rent);
                            newCamera.setStatus("Available");
                            CameraOperations co = new CameraOperations();
                            String res = co.addCamera(newCamera);
                            System.out.println(res);
                            run(user);
                        } catch (InputMismatchException e) {
                            System.out.println("INVALID INPUT");
                        }
                        break;
                    case 2:
                        CameraOperations co = new CameraOperations();
                        co.displayAllCameras();
                        System.out.print("ENTER THE CAMERA ID TO REMOVE - ");
                        int cameraId = 0;
                        try {
                            cameraId = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("INVALID INPUT.");
                        }
                        String msg = co.removeCamera(cameraId);
                        System.out.println(msg);
                        run(user);
                        break;
                    case 3:
                        CameraOperations obj = new CameraOperations();
                        obj.displayAllCameras();
                        run(user);
                        break;
                    case 4:
                        run(user);
                        break;
                    default:
                        System.out.println("INVALID CHOICE");
                        run(user);
                        break;
                }
                break;
            case 2:
                CameraOperations obj = new CameraOperations();
                System.out.println("FOLLOWING IS THE LIST OF AVAILABLE CAMERAS");
                obj.displayAllCameras("Available");
                System.out.print("ENTER THE CAMERA ID YOU WANT TO RENT - ");
                int cameraId = 0;
                try {
                    cameraId = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("INVALID INPUT.");
                }
                obj.RentCamera(user, cameraId);
                run(user);
                break;
            case 3:
                CameraOperations co = new CameraOperations();
                co.displayAllCameras();
                run(user);
                break;
            case 4:
                System.out.print("YOUR CURRENT WALLET BALANCE IS - INR."+user.getWalletBalance()+"\n");
                System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(1.YES 2. NO) - ");
                try {
                    int option = sc.nextInt();
                    if(option == 1) {
                        System.out.print("ENTER THE AMOUNT (INR) - ");
                        double amount = sc.nextDouble();
                        if(amount <= 0) {
                            System.out.println("AMOUNT MUST BE GREATER THAN 0");
                        }
                        else {
                            user.addToWallet(amount);
                            System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE - INR."+user.getWalletBalance());
                        }
                        run(user);
                    }
                    else if(option == 2) {
                        run(user);
                    }
                    else {
                        System.out.println("INVALID CHOICE");
                        run(user);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("INVALID INPUT");
                }
                break;
            case 5:
                break;
            default:
                System.out.println("INVALID CHOICE");
                run(user);
                break;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        boolean login = false;
        while(!login) {
            System.out.println("+---------------------------------------+");
            System.out.println("|\tWELCOME TO CAMERA RENTAL APP\t|");
            System.out.println("+---------------------------------------+");
            System.out.println("PLEASE LOGIN TO CONTINUE - ");
            System.out.print("USERNAME - ");
            String username = "",password = "";
            try {
                username = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("INVALID INPUT");
            }
            System.out.print("PASSWORD - ");
            try {
                password = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("INVALID INPUT");
            }
            if(!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
                System.out.println("INCORRECT CREDENTIALS");
            }
            else {
                login = true;
                run(user);
            }
        }
        sc.close();
    }
}
