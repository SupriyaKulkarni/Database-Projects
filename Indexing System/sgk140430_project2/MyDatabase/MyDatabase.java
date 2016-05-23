

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class MyDatabase {

	HashMap<Integer, ArrayList<Long>> mapIndexID = new HashMap<Integer, ArrayList<Long>>();
	Map<Integer, ArrayList<Long>> treeMapInt;
	Map<Float, ArrayList<Long>> treeMapFloat;
	Map<String, ArrayList<Long>> treeMapString;
	
	HashMap<String, ArrayList<Long>> mapIndexCompany = new HashMap<String, ArrayList<Long>>();
	

	HashMap<String, ArrayList<Long>> mapIndexdrugId = new HashMap<String, ArrayList<Long>>();
	

	HashMap<Integer, ArrayList<Long>> mapIndextrials = new HashMap<Integer, ArrayList<Long>>();
	
	
	
	HashMap<Integer, ArrayList<Long>> mapIndexpatient = new HashMap<Integer, ArrayList<Long>>();
	HashMap<Integer, ArrayList<Long>> mapIndexdosage = new HashMap<Integer, ArrayList<Long>>();
	HashMap<Float, ArrayList<Long>> mapIndexreading = new HashMap<Float, ArrayList<Long>>();
	HashMap<String, ArrayList<Long>> mapIndexdoubleblind = new HashMap<String, ArrayList<Long>>();
	HashMap<String, ArrayList<Long>> mapIndexcontrolled = new HashMap<String, ArrayList<Long>>();
	HashMap<String, ArrayList<Long>> mapIndexgovt_funded = new HashMap<String, ArrayList<Long>>();
	HashMap<String, ArrayList<Long>> mapIndexfdaapproved = new HashMap<String, ArrayList<Long>>();
	ArrayList<String> recordList = new ArrayList<String>();
	final static byte double_blind_mask      = 8;    // binary 0000 1000
	final static byte controlled_study_mask  = 4;    // binary 0000 0100
	final static byte govt_funded_mask       = 2;    // binary 0000 0010
	final static byte fda_approved_mask      = 1;    // binary 0000 0001

	
	// This function converts the CSV file to binary file 
   public  ArrayList<Long> ConvertToBinary()
   {
		String[] record;
		RandomAccessFile randomFile;
		ArrayList<Long> offsetList = new ArrayList<Long>();
		try {
			randomFile = new RandomAccessFile("data.db", "rw");
			File file = new File("PHARMA_TRIALS_1000B.csv");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			Byte commonByte;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null ) {
				commonByte=0x00;  
				offsetList.add(randomFile.getFilePointer());
			
				if(line.contains("\"")){
					line=line.replace("\"", "\\\"");
				}
				
		
			   record=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			   randomFile.writeInt(Integer.parseInt(record[0]));
			   if(record[1].contains("\\\"")){
				   record[1]=record[1].replace("\\\"", "");
			   }
			   randomFile.writeByte(record[1].length());
			   randomFile.writeBytes(record[1]);
			
			   randomFile.writeBytes(record[2]);
			   randomFile.writeShort(Integer.parseInt(record[3]));
			   randomFile.writeShort(Integer.parseInt(record[4]));
			   randomFile.writeShort(Integer.parseInt(record[5]));
			   randomFile.writeFloat(Float.parseFloat(record[6]));
			    if(record[7].equalsIgnoreCase("true")){
			    	commonByte = (byte)(commonByte | double_blind_mask);
			    }
			   if(record[8].equalsIgnoreCase("true"))
			    {
			    	commonByte = (byte)(commonByte | controlled_study_mask);
			    }
			    if(record[9].equalsIgnoreCase("true"))
			    {
			    	commonByte = (byte)(commonByte | govt_funded_mask);
			    }
			    if(record[10].equalsIgnoreCase("true"))
			    {
			    	commonByte = (byte)(commonByte | fda_approved_mask);
			    }
			    randomFile.writeByte(commonByte);
			   
			}
			
			fileReader.close();
			randomFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return offsetList;
	   
   }
	
	
	//This function generates index file
	public void createIndex(ArrayList<Long> offsetList) {

		String[] record;
		
		
		try {
			
			File file = new File("PHARMA_TRIALS_1000B.csv");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			int i=0;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null ) {
				
				if(line.contains("\"")){
					line=line.replace("\"", "\\\"");
				}
				
			   record=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			   if(record[1].contains("\\\"")){
				   record[1]=record[1].replace("\\\"", "");
			   }
				if (mapIndexID.containsKey(Integer.parseInt(record[0]))) {
					ArrayList<Long> nameList = mapIndexID.get(Integer.parseInt(record[0]));

					nameList.add(offsetList.get(i));
					mapIndexID.put(Integer.parseInt(record[0]), nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexID.put(Integer.parseInt(record[0]), nameList);
				}

				if (mapIndexCompany.containsKey(record[1])) {
					ArrayList<Long> nameList = mapIndexCompany.get(record[1]);

					nameList.add(offsetList.get(i));
					mapIndexCompany.put(record[1], nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexCompany.put(record[1], nameList);
				}
    
				

				if (mapIndexdrugId.containsKey(record[2])) {
					ArrayList<Long> nameList = mapIndexdrugId.get(record[2]);

					nameList.add(offsetList.get(i));
					mapIndexdrugId.put(record[2], nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexdrugId.put(record[2], nameList);
				}
				
				
				if (mapIndextrials.containsKey(Integer.parseInt(record[3]))) {
					ArrayList<Long> nameList = mapIndextrials.get(Integer.parseInt(record[3]));

					nameList.add(offsetList.get(i));
					mapIndextrials.put(Integer.parseInt(record[3]), nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndextrials.put(Integer.parseInt(record[3]), nameList);
				}
				
				
				if (mapIndexpatient.containsKey(Integer.parseInt(record[4]))) {
					ArrayList<Long> nameList = mapIndexpatient.get(Integer.parseInt(record[4]));

					nameList.add(offsetList.get(i));
					mapIndexpatient.put(Integer.parseInt(record[4]), nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexpatient.put(Integer.parseInt(record[4]), nameList);
				}
				
				if (mapIndexdosage.containsKey(Integer.parseInt(record[5]))) {
					ArrayList<Long> nameList = mapIndexdosage.get(Integer.parseInt(record[5]));

					nameList.add(offsetList.get(i));
					mapIndexdosage.put(Integer.parseInt(record[5]), nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexdosage.put(Integer.parseInt(record[5]), nameList);
				}
				
				if (mapIndexreading.containsKey(Float.parseFloat(record[6]))) {
					ArrayList<Long> nameList = mapIndexreading.get(Float.parseFloat(record[6]));

					nameList.add(offsetList.get(i));
					mapIndexreading.put(Float.parseFloat(record[6]), nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexreading.put(Float.parseFloat(record[6]), nameList);
				}
				
				if (mapIndexdoubleblind.containsKey(record[7])) {
					ArrayList<Long> nameList = mapIndexdoubleblind.get((record[7]));

					nameList.add(offsetList.get(i));
					mapIndexdoubleblind.put(record[7], nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexdoubleblind.put(record[7], nameList);
				}
				
				if (mapIndexcontrolled.containsKey(record[8])) {
					ArrayList<Long> nameList = mapIndexcontrolled.get((record[8]));

					nameList.add(offsetList.get(i));
					mapIndexcontrolled.put(record[8], nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexcontrolled.put(record[8], nameList);
				}
				
				if (mapIndexgovt_funded.containsKey(record[9])) {
					ArrayList<Long> nameList = mapIndexgovt_funded.get((record[9]));

					nameList.add(offsetList.get(i));
					mapIndexgovt_funded.put(record[9], nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexgovt_funded.put(record[9], nameList);
				}
				
				if (mapIndexfdaapproved.containsKey(record[10])) {
					ArrayList<Long> nameList = mapIndexfdaapproved.get((record[10]));

					nameList.add(offsetList.get(i));
					mapIndexfdaapproved.put(record[10], nameList);

				} else {
					
					ArrayList<Long> nameList = new ArrayList<Long>();
					nameList.add(offsetList.get(i));
					mapIndexfdaapproved.put(record[10], nameList);
				}
				
				
				i++;
				
			}
			treeMapInt = new TreeMap<Integer, ArrayList<Long>>(mapIndexID);
			treeMapFloat = new TreeMap<Float, ArrayList<Long>>(mapIndexreading);
			treeMapString=new TreeMap<String, ArrayList<Long>>(mapIndexCompany);
			
			
			
			writeToIndexFileInt(treeMapInt,"id.ndx");
			
			treeMapInt=mapIndextrials;
			writeToIndexFileInt(treeMapInt,"trials.ndx");
			
			treeMapInt=mapIndexpatient;
			writeToIndexFileInt(treeMapInt,"patients.ndx");
			
			treeMapInt=mapIndexdosage;
			writeToIndexFileInt(treeMapInt,"dosage_mg.ndx");
			
			writeToIndexFloat(treeMapFloat,"reading.ndx");
			
			writeToIndexstring(treeMapString,"company.ndx");
			treeMapString=mapIndexdrugId;
			writeToIndexstring(treeMapString,"drug_id.ndx");
			
			treeMapString=mapIndexdoubleblind;
			writeToIndexstring(treeMapString,"double_blind.ndx");
			
			treeMapString=mapIndexcontrolled;
			writeToIndexstring(treeMapString,"controlled_study.ndx");
			
			
			
			treeMapString=mapIndexgovt_funded;
			writeToIndexstring(treeMapString,"govt_funded.ndx");
			
			treeMapString=mapIndexfdaapproved;
			writeToIndexstring(treeMapString,"fda_approved.ndx");
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				// Put in the Hashmap for id

				
	}

	
	//This function writes to index file
	public static void writeToIndexFileInt(Map<Integer, ArrayList<Long>> treeMapId2,String fname) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fname, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Entry<Integer, ArrayList<Long>> entry : treeMapId2.entrySet()) {

			String temp = "[";
			ArrayList<Long> y = entry.getValue();

			if (y.size() == 1) {

				temp = temp + y.get(0).toString();

			} else {

				for (int i = 0; i < y.size(); i++) {

					if (i == 0) {
						temp = temp + y.get(i).toString();
					} else {

						temp = temp + "," + y.get(i).toString();
					}
				}
			}
			temp = temp + "]";
			writer.print(entry.getKey() + "=" + temp);
			writer.println();

		}
		writer.close();
		
	}

	//This function writes to index file

	public static void writeToIndexstring(
			Map<String, ArrayList<Long>> treeMapstring2,String fname) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fname, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Entry<String, ArrayList<Long>> entry : treeMapstring2.entrySet()) {

			String temp = "[";
			ArrayList<Long> y = entry.getValue();

			if (y.size() == 1) {

				temp = temp + y.get(0).toString();

			} else {

				for (int i = 0; i < y.size(); i++) {

					if (i == 0) {
						temp = temp + y.get(i).toString();
					} else {

						temp = temp + "," + y.get(i).toString();
					}
				}
			}
			temp = temp + "]";
			writer.print(entry.getKey() + "=" + temp);
			writer.println();

		}
		writer.close();
	}

	//This function writes to index file

	public static void writeToIndexFloat(
			Map<Float, ArrayList<Long>> treeMapFloat2,String fname) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fname, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Entry<Float, ArrayList<Long>> entry : treeMapFloat2.entrySet()) {

			String temp = "[";
			ArrayList<Long> y = entry.getValue();

			if (y.size() == 1) {

				temp = temp + y.get(0).toString();

			} else {

				for (int i = 0; i < y.size(); i++) {

					if (i == 0) {
						temp = temp + y.get(i).toString();
					} else {

						temp = temp + "," + y.get(i).toString();
					}
				}
			}
			temp = temp + "]";

			writer.print(entry.getKey() + "=" + temp);
			writer.println();

		}
		writer.close();
	}

	//This function is for the select query
	public void SelectField(String fileName,String value,int operator){
		ArrayList<Long> offsetList = new ArrayList<Long>();
		

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader(fileReader);
			String reader;
			
				while ((reader = buffer.readLine()) != null) {

					String[] array = reader.split("=");
					switch(operator){
					case 1:
						if(array[0].equalsIgnoreCase(value)){
							String temp = array[1].replace('[', ' ');

							temp = temp.replace(']', ' ');

							String[] tempArray = temp.split(",");
							for (int i = 0; i < tempArray.length; i++) {

								offsetList.add(Long.parseLong(tempArray[i].trim()));
							}
						}
						break;
					case 2:
						if(Integer.parseInt(array[0])<= Integer.parseInt(value)){
							String temp = array[1].replace('[', ' ');

							temp = temp.replace(']', ' ');

							String[] tempArray = temp.split(",");
							for (int i = 0; i < tempArray.length; i++) {

								offsetList.add(Long.parseLong(tempArray[i].trim()));
							}
						}
						
						break;
					case 3:
						if(Integer.parseInt(array[0])>= Integer.parseInt(value)){
							String temp = array[1].replace('[', ' ');

							temp = temp.replace(']', ' ');

							String[] tempArray = temp.split(",");
							for (int i = 0; i < tempArray.length; i++) {

								offsetList.add(Long.parseLong(tempArray[i].trim()));
							}
						}
						
						break;
					case 4:
						if(Integer.parseInt(array[0])> Integer.parseInt(value)){
							String temp = array[1].replace('[', ' ');

							temp = temp.replace(']', ' ');

							String[] tempArray = temp.split(",");
							for (int i = 0; i < tempArray.length; i++) {

								offsetList.add(Long.parseLong(tempArray[i].trim()));
							}
						}
						break;
						
					case 5:
						if(Integer.parseInt(array[0])< Integer.parseInt(value)){
							String temp = array[1].replace('[', ' ');

							temp = temp.replace(']', ' ');

							String[] tempArray = temp.split(",");
							for (int i = 0; i < tempArray.length; i++) {

								offsetList.add(Long.parseLong(tempArray[i].trim()));
							}
						}
						
						break;
					case 6:
						if(!(array[0].equalsIgnoreCase(value))){
							String temp = array[1].replace('[', ' ');

							temp = temp.replace(']', ' ');

							String[] tempArray = temp.split(",");
							for (int i = 0; i < tempArray.length; i++) {

								offsetList.add(Long.parseLong(tempArray[i].trim()));
							}
						}
						break;
					}
					

					
				
	}
				fileReader.close();
				buffer.close();
				FetchRecords(offsetList);
				
				
		}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public void FetchRecords(ArrayList<Long> offsetList){
		long address;
		String resultrecord;
		
		if(offsetList.size()==0){
			System.out.println("No records fetched");
		}
		else{
			
			System.out.println("id|company|drug_id|trials|patients|dosage_mg|reading|double_blind|controlled_study|govt_funded|fda_approved");
		for(int i=0;i < offsetList.size();i++){
				address=offsetList.get(i);
				resultrecord=FetchEachRecord(address);
				System.out.println(resultrecord);
				System.out.println("\n");
			}
		System.out.println("Number of records in the result= " + offsetList.size());
		}
		
	}
	
	
    public String FetchEachRecord(Long address){
    	RandomAccessFile randomFile;
    	StringBuilder sb = new StringBuilder();
    	ArrayList<Byte> record = new ArrayList<Byte>();
    	int len;
    	try {
			randomFile = new RandomAccessFile("data.db", "rw");
			
			randomFile.seek(address);
			sb.append(randomFile.readInt());
			sb.append("\t");
			len=randomFile.readByte();
			byte b[]  =new byte[len];
			randomFile.read(b,0,len);
			String cname=new String(b);
			sb.append(cname);
			sb.append("\t");
			
			byte b1[]= new byte[6];
			randomFile.read(b1,0,6);
			String DrugId=new String(b1);
			sb.append(DrugId);
			sb.append("\t");
			sb.append(randomFile.readShort());
			sb.append("\t");
			sb.append(randomFile.readShort());
			sb.append("\t");
			sb.append(randomFile.readShort());
			sb.append("\t");
			sb.append(randomFile.readFloat());
			sb.append("\t");
			byte commonByte;
			commonByte=randomFile.readByte();
			if(double_blind_mask == (byte)(commonByte & double_blind_mask)){
				sb.append("true");
			}
			else{
				sb.append("false");
			}
			sb.append("\t");
			
			if(controlled_study_mask == (byte)(commonByte& controlled_study_mask)){
				sb.append("true");
			}
			else{
				sb.append("false");
			}
			sb.append("\t");
		
			if(govt_funded_mask == (byte)(commonByte & govt_funded_mask)){
				sb.append("true");
			}
			else{
				sb.append("false");
			}
			sb.append("\t");
			
			
			if(fda_approved_mask == (byte)(commonByte & fda_approved_mask)){
				sb.append("true");
			}
			else{
				sb.append("false");
			}
			sb.append("\t");
			randomFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return sb.toString();
    }
	// Main method
	public static void main(String args[]) throws IOException {
		ArrayList<Long> address = new ArrayList<Long>();
		MyDatabase myDB = new MyDatabase();
		System.out.println("Enter your choice\n");
		System.out.println("1 for Creating Binary File");
		System.out.println("2 for Creating Index Files");
		System.out.println("3 for Querying");
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
	    String userChoice=br.readLine();
		
		switch(userChoice){
		case "1":
			address=myDB.ConvertToBinary();
			System.out.println("Binary file was created successfully");
			
			break;
		case "2":
			address=myDB.ConvertToBinary();
			 myDB.createIndex(address);
			 System.out.println("Index files was created successfully");
			 
			 break;
		case "3":
			File f1 =new File("data.db");
			File f2= new File("id.ndx");
			if(!f1.exists())
			{
				System.out.println("Binary File has not be created");
			}
			else if(!f2.exists()){
				System.out.println("Create the index file");
			}
			else{
				System.out.println("Enter the field to query on:");
				System.out.println("The field could be any one of these:id/company/drug_id/trials/patients/dosage_mg/reading/double_blind/controlled_study/govt_funded/fda_approved");
				String fieldname=br.readLine();
				fieldname=fieldname.trim();
				fieldname=fieldname+".ndx";
				File f3=new File(fieldname);
				if(!f3.exists()){
					System.out.println("Please enter a vaid field as shown above");
				}
				else{
				switch(fieldname){
				case "id.ndx":
				case"trials.ndx":
				case"patients.ndx":
				case"dosage_mg.ndx":
				case"reading.ndx":
					System.out.println("Enter the operator for the query:");
					System.out.println("< ,> ,<=,>=,!=,=");
					break;
				case "company.ndx":
				case "drug_id.ndx":
				case "double_blind.ndx":
				case "controlled_study.ndx":
				case "govt_funded.ndx":
				case "fda_approved.ndx":
					System.out.println("Enter the operator for the query:");
					System.out.println("!=,=");
					break;
				}
				String operator=br.readLine();
				System.out.println("Enter the value for comparison");
				String value=br.readLine();
				int opvalue=1;
				switch(operator){
				case "<":
					opvalue=5;
					break;
				case ">":
				opvalue=4;
				break;
				case "<=":
					opvalue=2;
					break;
				case ">=":
				opvalue=3;
				break;
				case "=":
					opvalue=1;
					break;
				case "!=":
					opvalue=6;
					break;
					default:
						System.out.println("By Default = operator is chosen");
						break;
				}
				myDB.SelectField(fieldname, value, opvalue);
			
				
			}
		}
		
		break;
		default:
			System.out.println("Invalid choice");
       break;
        
		

	}
		
		
			main(args);
	
		

}
}
