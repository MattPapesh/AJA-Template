package lower_level;

import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import javax.sound.sampled.*;

public class appAudio 
{
    private final String AUDIO_FILE_BASE_DIR = "audio_files/";
    private LinkedList<audioFile> audio_files = new LinkedList<audioFile>();

    private class audioFile
    {
        AudioInputStream audio_input_stream = null;
        Clip audio_clip = null; 
        String file_name = "";

        audioFile(String file_name)
        {
            try
            {   
                this.file_name = file_name;
                audio_input_stream = AudioSystem.getAudioInputStream(new File(AUDIO_FILE_BASE_DIR + file_name));
                audio_clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, audio_input_stream.getFormat()));
            
                audio_clip.open(audio_input_stream);
            }
            catch(UnsupportedAudioFileException e)
            {
                System.err.println("appAudio.java: Caught exeception! Audio file type is not a recognizable type!");
            }
            catch(IOException e)
            {
                System.err.println("appAudio.java: Caught exeception! Could not find the requested audio file!");
            }
            catch(LineUnavailableException e)
            {
                System.err.println("appAudio.java: Caught LineUnavailableException! ");
            }
            catch(NullPointerException e){} 
        }

        @Override
        protected void finalize() throws Throwable 
        {
            if(audio_clip != null)
            {
                audio_clip.close();
            }    
        }
    };

    @Override
    protected void finalize() throws Throwable 
    {
        if(audio_files != null)
        {
            audio_files.clear();
        }    
    }
    
    public void playAudioFile(String file_name)
    {
        audio_files.addLast(new audioFile(file_name)); 
        audio_files.getLast().audio_clip.start();
    }

    public void stopAudioFile(String file_name)
    {
        for(int i = 0; i < audio_files.size(); i++)
        {
            if(audio_files.get(i).file_name == file_name)
            {
                audio_files.get(i).audio_clip.stop();
                audio_files.get(i).audio_clip.stop(); 
            }
        }
    }
}
