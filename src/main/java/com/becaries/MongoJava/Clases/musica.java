package com.becaries.MongoJava.Clases;

//Clase que extiende de thread para lanzar un proceso que reproduzca musica en el reproductor de musica de windows
public class musica extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            // LLamamos al metodo
            playMp3();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void playMp3() throws Exception {
        // Obtenemos el directorio raiz del programa y anidamos al resto de la url que
        // esta e el prograa.
        // Una vez hecho ya se llama al cmd y se le pasa el comando
        String directoryName = System.getProperty("user.dir");
        new ProcessBuilder()
                .command("cmd.exe", "/c",
                        "start Wmplayer " + directoryName + "src/main/java/com/becaries/MongoJava/musica/nonoonon.mp3")
                .inheritIO().start().waitFor();

    }
}
