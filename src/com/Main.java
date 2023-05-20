package com;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static CameraOperations co = new CameraOperations();
    public static Scanner sc = new Scanner(System.in);
    public static String options[] = { "1. MY CAMERA", "2. RENT A CAMERA", "3. VIEW ALL CAMERAS", "4. MY WALLET",
            "5. EXIT" };
    public static String subOptions[] = { "1. ADD", "2. REMOVE", "3. VIEW MY CAMERAS", "4. GO TO PREVIOUS MENU" };

    public static void printOptions(String arr[]) {
        for (String str : arr) {
            System.out.println(str);
        }
    }

    public static void run(User user) {
        printOptions(options);
        int choice = 0;
        try {
            choice = sc.nextInt();
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
                            System.out.print("ENTER THE CAMERA BRAND - ");
                            String brand = sc.next();
                            System.out.print("ENTER THE MODEL - ");
                            String model = sc.next();
                            boolean validRent = false;
                            do {
                                System.out.print("ENTER THE PER DAY PRICE (INR) - ");
                                double rent = sc.nextDouble();
                                if(rent <= 0) {
                                    System.out.println("RENT MUST BE GREATER THAN ZERO.");
                                }
                                else {
                                    validRent = true;
                                    int cameraId = CameraOperations.cameraList.size() + 1;
                                    Camera newCamera = new Camera();
                                    newCamera.setCameraId(cameraId);
                                    newCamera.setBrand(brand);
                                    newCamera.setModel(model);
                                    newCamera.setRent(rent);
                                    newCamera.setStatus("Available");
                                    co.addCamera(newCamera);
                                }
                            }
                            while(!validRent);
                            run(user);
                            break;
                        case 2:
                            co.displayAllCameras("Available");
                            if(CameraOperations.cameraList.size() > 0) {
                                System.out.print("ENTER THE CAMERA ID TO REMOVE - ");
                                int id = sc.nextInt();
                                co.removeCamera(id);
                            }
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
                    if (CameraOperations.cameraList.size() == 0) {
                        System.out.println("No data present at this moment.");
                    } 
                    else {
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
                    }
                    run(user);
                    break;
                case 3:
                    co.displayAllCameras();
                    run(user);
                    break;
                case 4:
                    System.out.print("YOUR CURRENT WALLET BALANCE IS - INR." + user.getWalletBalance() + "\n");
                    System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(1.YES 2. NO) - ");
                    int option = sc.nextInt();
                    if (option == 1) {
                        System.out.print("ENTER THE AMOUNT (INR) - ");
                        double amount = sc.nextDouble();
                        if (amount <= 0) {
                            System.out.println("AMOUNT MUST BE GREATER THAN 0");
                        } 
                        else {
                            user.addToWallet(amount);
                            System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE - INR."+user.getWalletBalance());
                        }
                        run(user);
                    } 
                    else if (option == 2) {
                        run(user);
                    } 
                    else {
                        System.out.println("INVALID CHOICE");
                        run(user);
                    }
                    break;
                case 5:
                	System.out.println("Closing the application....\nThank you!");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    run(user);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID INPUT");
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        boolean login = false;
        System.out.println("+---------------------------------------+");
        System.out.println("|\tWELCOME TO CAMERA RENTAL APP\t|");
        System.out.println("+---------------------------------------+");
        while (!login) {
            System.out.println("PLEASE LOGIN TO CONTINUE - ");
            System.out.print("USERNAME - ");
            try {
                String username = sc.next();
                System.out.print("PASSWORD - ");
                String password = sc.next();
                if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
                    System.out.println("INCORRECT CREDENTIALS");
                } else {
                    login = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("INVALID INPUT");
            }
        }
        run(user);
        sc.close();
    }
}
