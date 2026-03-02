import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class NoteManager {
    private final String folderPath="notes_Str";

    public NoteManager() {
        File folder=new File(folderPath);
        if(!folder.exists()){
            folder.mkdir();
            System.out.println("Folder Created Successfully!");
        }
    }

            public void createNewNote(Scanner input){
                System.out.print("Enter a Unique Id name(eg. note-1) :");
                String id=input.nextLine().trim();

                File file=new File(folderPath+"/"+id+".txt");
                if(file.exists()){
                    System.out.println("Error! Already a file exist with that id !");
                    return;
                }

                System.out.println("Write the Notes:");
                String content=input.nextLine();

                try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
                    writer.write(content);
                    System.out.println("Note Added Successfully!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            public void viewAllnotes(){

                System.out.println("---All Notes is Here----");
                File folder=new File(folderPath);
                File[] files=folder.listFiles((dir, name) -> name.endsWith(".txt"));

                if(files==null || files.length==0){
                    System.out.println("No notes found!");
                }

                for(File f:files){
                    try{
                        String content=Files.readString(f.toPath());
                        System.out.println("ID:"+f.getName().replace(".txt","") +"||Content:"+content);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

                public void updateNote(Scanner input){
                    viewAllnotes();
                    System.out.print("Enter id (eg. note-12) to Update:");
                    String id=input.nextLine().trim();

                    File file=new File(folderPath+"/"+id+".txt");

                    if(!file.exists()){
                        System.out.println("Note not Found! Check Note Id!!");
                        return;
                    }

                    System.out.print("Update Type:- 1.Replace 2.Append || choice:");
                    String choice=input.nextLine();

                    System.out.print("Enter New Note:");
                    String newNote=input.nextLine();

                    boolean append=choice.equals("2");

                    try(BufferedWriter writer=new BufferedWriter(new FileWriter(file,append))){
                        if(append)writer.newLine();
                        writer.write(newNote);
                        System.out.println("Note updated Successfully!");
                    } catch (IOException e) {
                        System.out.println("Update failed!"+e.getMessage());
                    }

                }
                public void deleteNote(Scanner input){
                    viewAllnotes();
                    System.out.print("Enter Id to delete Note:");
                    String id=input.nextLine().trim();

                    File file=new File(folderPath +"/"+id+".txt");

                    if(file.delete()){
                        System.out.println("Note Deleted!");
                    }else{
                        System.out.println("Delete failed!Check if id is Incorrect!");
                    }
                }

                public void reset(){
                    File folder=new File(folderPath);
                    File[] file=folder.listFiles();

                    if(file != null){
                        for(File f:file) f.delete();
                    }
                    System.out.println("All note Cleared!");
                }

}
