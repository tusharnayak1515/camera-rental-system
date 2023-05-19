package com;
import java.util.ArrayList;
import java.util.Collections;

public class CameraOperations {
    static ArrayList<Camera> cameraList = new ArrayList<Camera>();
    
    public String addCamera(Camera cm) {
        cameraList.add(cm);
        Collections.sort(cameraList);
		return "YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST.";
	}

    public String removeCamera(int cameraId)
	{
        Camera camera = new Camera();
        for(Camera obj:cameraList) {
            if(obj.getCameraId() == cameraId) {
                camera = obj;
            }
        }

        if(camera.getStatus().equals("Rented")) {
            return "CAMERA CANNOT BE REMOVED AS IT IS RENTED.";
        }
        cameraList.remove(camera);
        Collections.sort(cameraList);;
        return "CAMERA SUCCESSFULLY REMOVED FROM THE LIST.";
	} 

    public void RentCamera(User user,int cameraId)
    {
        Camera camera = new Camera();
        for(Camera obj:cameraList) {
            if(obj.getCameraId() == cameraId) {
                camera = obj;
            }
        }

        if(camera.getStatus().equals("Rented")) {
            System.out.println("CAMERA IS ALREADY RENTED.");
        }
        else {
            double balance = user.getWalletBalance();
            if(balance >= camera.getRent()) {
                camera.setStatus("Rented");
                user.setWalletBalance(balance-camera.getRent());
                System.out.println("YOUR TRANSACTION FOR CAMERA - "+camera.getBrand()+" "+camera.getModel()+" with rent INR."+camera.getRent()+" HAS SUCCESSFULLY COMPLETED.");
            }
            else {
                System.out.println("ERROR: TRANSACTION FAILED DUE TO INSUFFICIENT WALLET BALANCE. PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET.");
            }
        }
    }

    public void displayAllCameras() {
        if(cameraList.size() == 0) {
            System.out.println("No data present at this moment.");
        }
        else {
            System.out.println("====================================================================================================");
            System.out.println("CAMERA ID\t\tBRAND\t\tMODEL\t\tPRICE(PER DAY)\t\tSTATUS");
            System.out.println("====================================================================================================");
            for(Camera camera:cameraList) {
                System.out.print(camera.getCameraId()+"\t\t\t"+camera.getBrand()+"\t\t"+camera.getModel()+"\t\t"+camera.getRent()+"\t\t\t"+camera.getStatus()+"\n");
            }
            System.out.println("====================================================================================================");
        }
	}

    public void displayAllCameras(String status) {
        if(cameraList.size() == 0) {
            System.out.println("No data present at this moment.");
        }
        else {
            System.out.println("====================================================================================================");
            System.out.println("CAMERA ID\t\tBRAND\t\tMODEL\t\tPRICE(PER DAY)\t\tSTATUS");
            System.out.println("====================================================================================================");
            for(Camera camera:cameraList) {
                if(camera.getStatus().equals(status)) {
                    System.out.print(camera.getCameraId()+"\t\t\t"+camera.getBrand()+"\t\t"+camera.getModel()+"\t\t"+camera.getRent()+"\t\t\t"+camera.getStatus()+"\n");
                }
            }
            System.out.println("====================================================================================================");
        }
	}

}