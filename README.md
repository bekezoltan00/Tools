# Tools
## Projekt Eszközök beadandó

###Program leírás
A felhasználó egy ablakból tudja kiválasztani, melyik játékkal szeretne játszani. A megfelelő gomb megnyomása után egy új ablakban jelenik meg a kiválasztott játék. Amennyiben a felhasználó már nem szeretne tovább játszani, egyszerűen csak kilép az aktuális felületből, ami után visszatérhet a főmenübe. A felhasználó hat játék közül tud választani, amik között van Single player (például: Labirintus), és van Multiplayer-es (például: Fénymotorok) játék is, így akár könnyed szórakozást nyújthat két személy esetén is az alkalmazás, de egyedüli személyként is kiváló élményt ad.
![Főmenü](img/mainMenu.png?raw=true "Főmenü")
![Fénymotor játék](img/gameMotors.png?raw=true "Fénymotorok")

###Kivitelezés
A beadandóban a következő feladatot valósítottuk meg: A régi már elkészített beadandóinkat felhasználva, létrehoztunk egy új projektet, amihez hozzáadtuk azokat. Minden beimportált alkalmazás után a csapat többi tagja a verziókezelő (git) segítségével letöltötte a számára újonnan feltöltött részeket, hogy elérhetővé váljon mindenki számára. Majd a különböző alkalmazások egybefésülése következett, egy új főosztályt hoztunk létre, ami a jelenlegi projektünk indítója. Itt jelenítjük meg az egyes játékok indítógombjait, amik külön ablakban tárulnak elénk, azok megnyomásának hatására. A program így készen áll a tesztelésre. 
Az Eclipse fejlesztőkörnyezet segítségével az elkészült programból egy Java dokumentációt generáltunk, ami szintén elérhető a projekt fájlai között.
[Javadoc](doc/index.html)

###Tesztelés
A tesztelésben az egyes osztályok néhány metódusának ellenőrzésére fektettük a hangsúlyt. Ezeket a fájlokat egy külön test nevű mappába helyeztük, aszerint csoportosítva, hogy melyik teszt, melyik osztályhoz tartozik. Több, eltérő tesztesetet kreáltunk. Néhány teszteset:
	Tömb túlindexelés:
		@org.junit.Test(expected=ArrayIndexOutOfBoundsException.class)
		public void shouldReturnArrayIndexOutOfBoundsExceptionWhenOutOfButtonArray() {
			KnightView kv = new KnightView();
			assertEquals(kv.getButtons()[5][5].getBackground(), Color.WHITE);
		}
	Megfelelő mozgási irány:
		@org.junit.Test
		public void correctMovedUp(){
			int size = 10;
			LabirintusModel lm = new LabirintusModel(size);
			Field[][] fields = lm.getFields();
			if(!fields[size-2][0].isWall()){
				lm.moveUp();
				assertEquals(lm.getColor(size-2, 0),Color.yellow);
			}else{
				lm.moveUp();
				assertEquals(lm.getColor(size-2, 0),Color.gray);
			}
			
		}
	Korrekt mennyiségű gomb létrehozása:
		@org.junit.Test
		public void correctSizeOfButtonsArray() {
			ColorView color = new ColorView();
			int arraySize=0;
			for(int i=0; i< color.getButtons().length; i++){
				arraySize += color.getButtons()[i].length;
			}
			assertEquals(arraySize, 81);
		}


###Verziókezelés
A beadandó feladatban verziókezelőnek a git-et válaszottuk, a hozzátartozó github.com-os felülettel. Feltelepítettük a git asztali változatát (asztali, parancssoros), majd git clone parancshívást követően lemásoltuk az általunk kiválasztott helyre. Ezután az alábbi parancsokat alkalmaztuk:
	git status
	git pull origin master
	git add .
	git commit -m <"A módosítás neve röviden">
	git push origin master
