package Packij;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class HighScoreHandler {

    //pls note that everything in this is package-private or just private

    Component parent;


    private ArrayList<ScoreRecord> highScores;
    //arrayList of scoreRecord objects, exists throughout runtime, even if the high score file itself goes missing

    private String fileLocation; //location of the high score file

    private File scoreFile; //reference to the high score file

    private boolean fileExists; //tracks whether or not the high score file exists


    HighScoreHandler(Component c){

        parent = c;

        highScores = new ArrayList<>(); //initialises the highScores arrayList

        fileLocation = "lookAtTheseNerdsLmao/gallowboob-impersonators.txt";

        scoreFile = new File(fileLocation);
        //initialises the fileLocation string and scoreFile (which is a reference to the file at fileLocation)


        String nameLine;
        String scoreLine;
        int i = 0;
        //some temporary variables
        //nameLine will hold the line of the highScoreFile with the current player's name, scoreLine is same but for their score

        //will now attempt to read the high score file and add the details about the score to the file
        try{
            fileExists = true; //file exists until proven otherwise
            FileReader fr = new FileReader(fileLocation);
            BufferedReader br = new BufferedReader(fr);
            //pretty much setting up the stuff for reading the file

            //until the end of the file is reached, it will construct a score record for everything in the file
            while ((nameLine = br.readLine())!=null) {
                scoreLine = br.readLine(); //scoreLine is 2nd line read
                if (scoreLine == null){
                    scoreLine = "0"; //set to '0' if blank
                }
                highScores.add(new ScoreRecord(nameLine,scoreLine));
                //nameLine and scoreLine used to construct a ScoreRecord, which is added to highScores
                i++;
            }
            br.close(); //closes the bufferedReader

            if (i < 5){
                placeholderHighScores(i);
            }

        } catch (FileNotFoundException e){
            //if the file couldn't be found
            fileExists = false;
            System.out.println("get a load of this downronner");
            placeholderHighScores(i);
            attemptToMakeFile();
            //will attempt to create the file (and fill it)
        } catch (Exception ex){
            placeholderHighScores(i);
            System.out.println("yeah something weird happened");
            ex.printStackTrace();
            //covers anything else that may be thrown
        }

        Collections.sort(highScores);
        Collections.reverse(highScores);
    }

    void recordHighScore(int newScore){

        JOptionPane.showMessageDialog(
                parent,
                "Ron \"Ron Paul\" Paul can still win!\n"
                        +"#Ron\"RonPaul\"Paul2012",
                "downronners smh my head",
                JOptionPane.PLAIN_MESSAGE
        );


        int pos = 0; //the position of the new score relative to the rest of the scoreboard
        for(ScoreRecord sr:highScores){
            if (newScore > sr.getScore()){
                break;
                //end the loop if the new score is greater than the score in the current ScoreRecord object
            }
            pos++; //increase pos if it's not greater than the current score
        }

        String scoreMessage; //initalises message for the user
        if (pos == 0){
            scoreMessage ="oh hello there gallowboob\n\n"
                    +"you are number "+ (pos+1) +" on the leaderboard\n\n"
                    +"but you still lost lol.\n\n"
                    +"Your score was:\n"+ newScore + "\n\n"
                    +"pls enter your name for the leaderboard.";

        } else if (pos < 5){

            //a very rewarding message if the player has a score in the top 5 scores
            scoreMessage ="u are a true upronner\n\n"
                    +"you are number "+ (pos+1) +" on the leaderboard!\n\n"
                    +"but you still lost.\n"
                    +"Ron \"Ron Paul\" Paul is disappointed in you.\n\n"
                    +"Your score was:\n"+ newScore + "\n\n"
                    +"Please enter your name, so it can be recorded.";

        } else{
            //less rewarding message if the player did not get a high score.
            scoreMessage = "get a load of this downronner\n\n"
                    +"your score: " + newScore + "\n"
                    +"your position: " + (pos+1) + "\n\n"
                    +"Ron\"Ron Paul\" Paul knows that you can do better than that!\n"
                    +"smh my head\n\n"
                    +"Please enter your name, so it can be recorded on the leaderboard.";
        }

        String scoreName;
        scoreName = JOptionPane.showInputDialog(
                parent,
                scoreMessage,
                "GAME OVER",
                JOptionPane.PLAIN_MESSAGE
        ); //user basically asked for their name


        //constructs a new ScoreRecord object for this score
        ScoreRecord thisScore = new ScoreRecord(scoreName, newScore);

        highScores.add(pos,thisScore);
        //adds new score to the appropriate place in the HighScores ArrayList, and removes the last item from it

        if (fileExists){
            try {
                fillFile(); //attempts to fill the file, if it existed when it last checked
            } catch (FileNotFoundException e){
                System.out.println("Error accessing the high score file!");
                fileExists = false;
                //if it doesn't exist now, complains about it and records that it doesn't exist
                attemptToMakeFile(); //will attempt to re-make and fill the file again
            }
            catch (Exception e) {
                System.out.println("Error updating the high score file!");
                //can't update the file if something else prevents it from doing that
            }
        } else{
            attemptToMakeFile();
            //attempts again to create the file (and fill it) if it still doesn't exist yet
        }

        JOptionPane.showMessageDialog(
                parent,
                scoresToString(),
                "leaderboard",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    String scoresToString(){
        int i = 0;
        StringBuilder scoreString = new StringBuilder();
        while (i < 5){
            scoreString.append(highScores.get(i).toString()).append("\n");
            i++;
        }
        return scoreString.toString();
        //basically puts the contents of the top 5 ScoreRecords in a single, multi-line string, which is then returned
        //adds an extra newline between each score for human-readability reasons too
    }



    private void placeholderHighScores(int i){
        //basically fills out the part of the highScores arrayList which hasn't been filled with some high scores
        //ensures that at least 5 records will be present in highScores

        //just adds placeholder values though
        while (i < 5) {
            highScores.add(new ScoreRecord("unknown",0));
            i++;
        }
    }

    private void fillFile() throws IOException{
        //writes the contents of the highScores ArrayList to the high score file
        FileWriter tempWriter = new FileWriter(fileLocation);
        for(ScoreRecord sr: highScores){
            tempWriter.write(sr.toString());
        }
        tempWriter.close();
    }

    private void attemptToMakeFile(){
        try{
            //will attempt to create a new high score file
            if (scoreFile.createNewFile()){
                //if successful, attempts to fill it with the contents of highScores
                fillFile();
                //will only note that it exists after the contents have successfully been added
                fileExists = true;
                System.out.println("high score file has been created!");
            } else{
                System.out.println("couldn't make a new high score file, Fs in chat");
                //error message if the file could not be made
            }
        } catch (Exception e){
            System.out.println("an exception stopped the creation of a new high score file, Fs in chat");
            //error message if there's an exception stopping the creation of a high score file
            e.printStackTrace();
        }
    }


}

class ScoreRecord implements Comparable {

    //basically contains a String object to hold a player's name, and an Integer object to hold their score
    private String name;
    private Integer theScore;

    ScoreRecord(String scoreName, String score){
        //this constructor is called when HighScoreHandler is reading from the high score file
        setName(scoreName); //attempts to initialise their name
        try {
            //attempts to get an Integer theScore from the String score
            this.theScore = Integer.parseInt(score);
        } catch (Exception e){
            //sets theScore to 0 if it fails
            this.theScore = 0;
        }
    }

    ScoreRecord(String scoreName, int score) {
        //this constructor is called when HighScoreHandler is constructing a score achieved by the current player
        setName(scoreName); //attempts to initialise their name
        this.theScore = score; //theScore set to value of score
    }

    private void setName(String scoreName){
        if (scoreName == null ||  scoreName.isEmpty()){
            this.name = "some rando"; //name set to unknown if scoreName is empty
        } else{
            this.name = scoreName; //otherwise name set to scoreName
        }
    }

    Integer getScore(){
        return theScore;
    } //returns 'theScore' of this ScoreRecord


    @Override
    public String toString(){
        return name + "\n" + theScore + "\n";
        //name on first line of string, theScore on 2nd line, and followed by a newline
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof Packij.ScoreRecord){
            return this.getScore().compareTo(((Packij.ScoreRecord) o).getScore());
            //compares the 'theScore' value of this to the 'theScore' value of the other ScoreRecord
        } else{
            return 0; //can't really compare the other object if it isn't a scoreRecord, y'know?
        }
    }

}
