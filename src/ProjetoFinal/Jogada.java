package ProjetoFinal;

public class Jogada {
    //Método retorna verdadeiro ou falso para se o jogador acertou o barco do adversário
    public static boolean tiro (String[][] tabuleiro, int x, int y) {

        if(tabuleiro[x][y].equals("|XXXXX")||tabuleiro[x][y].equals("|XXXXX|")){
            return true;
        } else if(tabuleiro[x][y].equals("|BOOOM|")||tabuleiro[x][y].equals("|BOOOM")||tabuleiro[x][y].equals("|^^^^^|")||tabuleiro[x][y].equals("|^^^^^")){
            System.out.println("VocÃª já realizou esta jogada e perdeu a vez.");
            return false;
        } else {
            return false;
        }

    }
    // Em caso de acerto, o vetor é modificado, caso contrário também
    public static String[][] acertou(String[][]tabuleiro, int x, int y){
        if(y==9) {
            tabuleiro[x][y] = "|BOOOM|";
        } else {
            tabuleiro[x][y] = "|BOOOM";
        }

        return tabuleiro;
    }

    public static String[][] errow(String[][]tabuleiro, int x, int y) {
        if(y==9) {
            tabuleiro[x][y] = "|^^^^^|";
        } else {
            tabuleiro[x][y] = "|^^^^^";
        }

        return tabuleiro;
    }
}
