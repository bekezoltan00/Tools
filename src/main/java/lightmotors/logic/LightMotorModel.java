
package lightmotors.logic;

import java.awt.Point;



/**
 *
 * @author Kristóf
 */
public class LightMotorModel {

    private Field[][] fields;
    private int size;
    private Point p1;
    private Point p2;
    private Point v1;
    private Point v2;
    private boolean rright = false;
    private boolean rleft = true;
    private boolean rup = false;
    private boolean rdown = false;
    private boolean bright = true;
    private boolean bleft = false;
    private boolean bup = false;
    private boolean bdown = false;

    public int move() {
        int  ok = 0;
        if (checkTie()) {
            ok = 3;
        } else {

            if (checkP1()) {
                ok = 1;
            } else {
                p1.x += v1.x;
                p1.y += v1.y;
            }

            if (checkP2()) {
                ok = 2;
            } else {
                p2.x += v2.x;
                p2.y += v2.y;
            }
        }
        fields[p1.x][p1.y].setInfo(1);
        fields[p2.x][p2.y].setInfo(2);
        return ok;
    }

    public void changeDirictionRed(int diraction) {
        switch (diraction) {
            case 0: {
               if(!rdown){
                   //fel
                v1.y = 0;
                v1.x = -1;
                rup=true;
                rleft=false;
                rright=false;
                }
            }
            break;
            case 1: {
                 //jbbra
                v1.y = 1;
                v1.x = 0;
             
            }
            break;
            case 2: {//le
                v1.y = 0;
                v1.x = 1;
            }
            break;
            case 3: {//balra
                v1.y = -1;
                v1.x = 0;
            }
            break;
        }
    }

    public void changeDirictionBlue(int i) {
        switch (i) {
            case 0: {//fel
                v2.y = 0;
                v2.x = -1;

            }
            break;
            case 1: {//jbbra
                v2.y = 1;
                v2.x = 0;
            }
            break;
            case 2: {//le
                v2.y = 0;
                v2.x = 1;
            }
            break;
            case 3: {//balra
                v2.y = -1;
                v2.x = 0;
            }
            break;
        }
    }

    public int getNumber(int i, int j) {
        return fields[i][j].getInfo();
    }

    public void newGame(int size) {
        this.size = size;
        fields = new Field[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i == 0) || (j == 0) || (i == size - 1) || (j == size - 1)) {
                    fields[i][j] = new Field(3);
                } else {
                    if (i == size / 2 && j == size - 2) {
                        fields[i][j] = new Field(1);
                        p1 = new Point(i, j);
                    } else {
                        if (i == size / 2 && j == 1) {
                            fields[i][j] = new Field(2);
                            p2 = new Point(i, j);
                        } else {
                            fields[i][j] = new Field(0);
                        }
                    }
                }
            }
        }
       rright = false;
        rleft = true;
        rup = false;
        rdown = false;
        bright = true;
        bleft = false;
        bup = false;
        bdown = false;
        v1 = new Point(0, -1);
        v2 = new Point(0, 1);
    }

    private boolean checkP1() {
       return ((fields[p1.x + v1.x][p1.y + v1.y].getInfo() == 2) ||
               (fields[p1.x + v1.x][p1.y + v1.y].getInfo() == 1) ||
              ((p1.x + v1.x == 0) || (p1.x + v1.x == size - 1) || (p1.y + v1.y == 0) || (p1.y + v1.y == size - 1)));
    }

    private boolean checkP2() {
       return ((fields[p2.x + v2.x][p2.y + v2.y].getInfo() == 2) ||
               (fields[p2.x + v2.x][p2.y + v2.y].getInfo() == 1) ||
              ((p2.x + v2.x == 0) || (p2.x + v2.x == size - 1) || (p2.y + v2.y == 0) || (p2.y + v2.y == size - 1)));
    }

    private boolean checkTie() {
        return ((fields[p2.x + v2.x][p2.y + v2.y].getInfo() == 1) && (fields[p1.x + v1.x][p1.y + v1.y].getInfo() == 2))||
                (((p2.x + v2.x == 0) || (p2.x + v2.x == size - 1) || (p2.y + v2.y == 0) || (p2.y + v2.y == size - 1)) &&
                ((p1.x + v1.x == 0) || (p1.x + v1.x == size - 1) || (p1.y + v1.y == 0) || (p1.y + v1.y == size - 1)))
                ;

    }

}
