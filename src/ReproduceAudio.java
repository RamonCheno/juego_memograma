import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReproduceAudio {
private ArrayList<File> EFX = new ArrayList<>();
	
	public ReproduceAudio() {
		IntroducirFx("Efecto0.wav");
	}
	
	public void IntroducirFx(String Ruta) {
		try {

			File file = new File("").getAbsoluteFile();
					
			String rutt = file + "/assets/sound/" + Ruta;

			file = new File(rutt);
			
			EFX.add(file);
			
		}catch(NullPointerException e) {
			System.out.println("Error la ruta o archivo no encontrtado de audio......");
			
		}
	}
	
	public void Fx (int indice) {
		
		try {
		File file = EFX.get(indice);
		
		Clip sonido = AudioSystem.getClip();
		
		sonido.open(AudioSystem.getAudioInputStream(file));
		
		sonido.start();
		System.out.println("Archivo compatible");
		} catch (LineUnavailableException ex) {
			Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
		}catch (UnsupportedAudioFileException ex) {
			Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("archivo no compatible");
		} catch (IOException ex) {
			Logger.getLogger(ReproduceAudio.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
