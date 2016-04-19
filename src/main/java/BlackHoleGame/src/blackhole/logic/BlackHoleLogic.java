package blackhole.logic;

import java.util.ArrayList;

public class BlackHoleLogic {

    private final int[][] area;
    private final int n;
    private int whichPlayer;

    public BlackHoleLogic(int size) {
        n = size;
        area = new int[size][size];
        newGame();
    }

    public int getN() {
        return n;
    }

    public int getfield(int x, int y) {
        return area[x][y];
    }
    
    public final void newGame() {
        whichPlayer = 1;                            // melyik játékokos kezdi a játékot
        int player = 1;
        for (int i = 0; i < n; i++) {               
            if (i == n / 2) {                       //feketelyuk sorátol mar a masik jatékos űrhajóji vannak
                player = 2;
            }
            for (int j = 0; j < n; j++) {
                if (j == i || ((n - 1) - i == j)) { //főátló + mellékátló
                    area[i][j] = player;
                } else {
                    area[i][j] = 0;
                }
            }
        }
        area[n/2][n/2]=3;                           //fekete lyuk
    }

    public void move(int x, int y, int direction) {
        int player = area[x][y];                //ahonnan lép a játékos, azt a mezőt eltárolja
        area[x][y] = 0;                         //törli azt a mezőt
        switch (direction) {                    //meghatározza az új pozícióját a soronkövezkező játékosnak 
            case 0: { //jobbra
                while (y + 1 < n && area[x][y + 1] == 0) {//addig megy az űrhajó míg űr van (area[x][y + 1]=0 )
                    y++;
                }
                if (!isBlackHole(x, y + 1)) {   //ha az űrhajó egy fekete lyuk előtt állt meg, akkor a feketelyuk "beszimpantja"
                    area[x][y] = player;
                }
                break;
            }
            case 1: { //balra
                while (y - 1 >= 0 && area[x][y - 1] == 0) {
                    y--;
                }
                if (!isBlackHole(x, y - 1)) {
                    area[x][y] = player;
                }
                break;
            }
            case 2: {   //fel
                while (x - 1 >= 0 && area[x - 1][y] == 0) {
                    x--;
                }
                if (!isBlackHole(x - 1, y)) {
                    area[x][y] = player;
                }
                break;
            }
            case 3: {   //le
                while (x + 1 < n && area[x + 1][y] == 0) {
                    x++;
                }
                if (!isBlackHole(x + 1, y)) {
                    area[x][y] = player;
                }
                break;
            }
        }
        changePlayer();
    }

    private void changePlayer() {
        if (getWhichPlayer() == 1) {
            setWhichPlayer(2);
        } else {
            setWhichPlayer(1);
        }
    }

    public boolean isBlackHole(int x, int y) {
        try {
            return (area[x][y] == 3);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public int isGameWon() {//akkor nyert valaki, ha n/2 hajója maradt
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j] == 1) {
                    count1++;
                }
                if (area[i][j] == 2) {
                    count2++;
                }
            }
        }
        return (n / 2 == count1 ? 1 : n / 2 == count2 ? 2 : 0); //0-at ha nem nyert senki, különben a győztes játékos sorszámát adja vissza
    }

    public ArrayList<Integer> passableWays(int x, int y) {
        ArrayList list = new ArrayList<>();
        try {
            if (area[x][y + 1] != 0 && !isBlackHole(x, y + 1)) {//ha van valami abba az irányban akkor
                list.add(0);                                    // => nem mehet abba az irányba a kiválaszott űrhajónk, kivéve persze ha fekelyuk van az útjában 
            }
        } catch (IndexOutOfBoundsException e) {                 //ha exception-t kapok  akkor valamelyik szélén vagyok a pályának
            list.add(0);                                        //=> nem mehet abba az irányba a kiválaszott űrhajó
        }
        try {
            if (area[x][y - 1] != 0 && !isBlackHole(x, y - 1)) {
                list.add(1);
            }
        } catch (IndexOutOfBoundsException e) {
            list.add(1);
        }
        try {

            if (area[x - 1][y] != 0 && !isBlackHole(x - 1, y)) {
                list.add(2);
            }
        } catch (IndexOutOfBoundsException e) {
            list.add(2);
        }
        try {

            if (area[x + 1][y] != 0 && !isBlackHole(x + 1, y)) {
                list.add(3);
            }
        } catch (IndexOutOfBoundsException e) {
            list.add(3);
        }
        return list;                                            //ArrayListbe adja vissza azoknak az irányokat,amerre nem mehet az űrhajó
    }

    public int getWhichPlayer() {
        return whichPlayer;
    }

    public void setWhichPlayer(int whichPlayer) {
        this.whichPlayer = whichPlayer;
    }

    public boolean playerTurn(int x, int y) {
        return (getfield(x, y) == getWhichPlayer() && getfield(x, y) != 3);
    }
}
