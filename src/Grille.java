import java.util.Random;

/**
 *
 * @author Oreste
 */
public class Grille
{
    private int nbLig;
    private int nbCol;
    private int[][] tab; // -1 : vie , 0 - 8 : info sur les vies autour
    //private int[][] visibility; // 0 : non creusée, 1 : creusée, 2 : drapeau

    public Grille(int lc, int nbVies)
    {
        // Création de la Grille
        Random r = new Random();
        setNbLig(lc);
        setNbCol(lc);

        int[][] tab = new int[lc][lc];
        for (int i = 0; i < lc; i++) {
            for (int j = 0; j < lc; j++) {
                tab[i][j] = 0;
            }
        }
        int m = 0;
        while (m < nbVies) {
            int x = r.nextInt(lc);
            int y = r.nextInt(lc);
            if (tab[x][y] == 0) // Sinon il y a déjà une vie à cet emplacement
            {
                tab[x][y] = -1;
                m++;
            }
        }
        setTab(tab);
        // On remplit les autres cases
        for (int i = 0; i < lc; i++) {
            for (int j = 0; j < lc; j++) {
                if (tab[i][j] != -1) {
                    tab[i][j] = nbLivesAround( i, j);
                }
            }
        }
    }
    public Grille(int l,int c, int nbVies)
    {
        // Création de la Grille
        Random r = new Random();
        setNbLig(l);
        setNbCol(c);

        int[][] tab = new int[l][c];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                tab[i][j] = 0;
            }
        }
        int m = 0;
        while (m < nbVies) {
            int x = r.nextInt(l);
            int y = r.nextInt(c);
            if (tab[x][y] == 0) // Sinon il y a déjà une vie à cet emplacement
            {
                tab[x][y] = -1;
                m++;
            }
        }
        setTab(tab);
        // On remplit les autres cases
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < c; j++) {
                if (tab[i][j] != -1) {
                    tab[i][j] = nbLivesAround( i, j);
                }
            }
        }
    }

    public int getNbLig() {
        return nbLig;
    }

    public void setNbLig(int nbLig) {
        this.nbLig = nbLig;
    }

    public int getNbCol() {
        return nbCol;
    }

    public void setNbCol(int nbCol) {
        this.nbCol = nbCol;
    }

    public int[][] getTab() {
        return tab;
    }

    public void setTab(int[][] tab) {
        this.tab = tab;
    }

    /**
     * Compte le nombre de vies autour de la position (x,y) de la Grille this
     *
     * @return Le nombre de vies calculé
     */
    public int nbLivesAround(int x, int y) {
        int nb = 0;
        if (isLife(x - 1, y - 1)) {
            nb++;
        }
        if (isLife(x, y - 1)) {
            nb++;
        }
        if (isLife(x + 1, y - 1)) {
            nb++;
        }
        if (isLife(x - 1, y)) {
            nb++;
        }
        if (isLife(x + 1, y)) {
            nb++;
        }
        if (isLife(x - 1, y + 1)) {
            nb++;
        }
        if (isLife(x, y + 1)) {
            nb++;
        }
        if (isLife(x + 1, y + 1)) {
            nb++;
        }
        return nb;
    }

    /**
     * Test pour savoir s'il y a une vie à la position (x,y) de la Grille this
     *
     * @return La valeur du test booléen
     */
    public boolean isLife(int x, int y) {
        if (x < 0 || x > getNbLig() - 1 || y < 0 || y > getNbCol() - 1) {
            return false;
        }
        if (getTab()[x][y] == -1) {
            return true;
        }
        return false;
    }

    /**
     * Une cellule vide devient pleine si elle a exactement 3 voisins
     * Une cellule pleine continue de l'etre si elle a 2 ou 3 voisin
     * Une cellule pleine si elle a mois de 2 voisin elle mort d'isolement
     * Une cellule pleine , si elle a plus de 3 voisins elle meurt de surpopulation
     */
    public void play() {
        int[][] newTab = new int[getNbLig()][getNbCol()];

        // Iterate through each cell in the grid
        for (int i = 0; i < getNbLig(); i++) {
            for (int j = 0; j < getNbCol(); j++) {
                int liveNeighbors = nbLivesAround(i, j);

                if (isLife(i, j)) {
                    // Any live cell with 2 or 3 live neighbors survives.
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        newTab[i][j] = -1;
                    } else {
                        // Any live cell with fewer than 2 or more than 3 live neighbors dies.
                        newTab[i][j] = 0;
                    }
                } else {
                    // Any dead cell with exactly 3 live neighbors becomes a live cell.
                    if (liveNeighbors == 3) {
                        newTab[i][j] = -1;
                    } else {
                        newTab[i][j] = 0;
                    }
                }
            }
        }


        // Update the grid with the new state
        setTab(newTab);
        updateNeighbors();
    }
    private void updateNeighbors(){
        for (int i = 0; i < getNbLig(); i++) {
            for (int j = 0; j < getNbCol(); j++) {
                if(!isLife(i,j)){
                    tab[i][j]=nbLivesAround(i,j);
                }
            }
        }
    }

}
