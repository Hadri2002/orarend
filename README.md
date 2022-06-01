Tantárgy kezelő program (Neptun copy)
=========

**A program célja**

A program segítségével a programtervező informatikus hallgatók fel tudják venni tantárgyaikat félévenként, illetve ki tudják számítani az átlagukat, KKI-t.

Package-ek
-----

**FIO**

Ez a package tartalmazza a fájl írással, illetve olvasással kapcsolatos metódusokat.

*Osztályai:*
* Fio

**Business**

A business package adja meg a program logikai alapját, ez végzi el az összes műveletet

*Osztályai:*
* FelvehetoTantargy
* TeljesitettTantargy
* OsztalyzatEnum
* GetterFunctionName
* Methods

**Business.os**

A business package-en belüli ős package

*Osztályai:*
* Tantargy

**Controller**

A User Interface kezelésével foglalkozó package

*Osztályai:*
* Menu


Osztályok
------

**Tantargy, FelvehetoTantargy, TeljesitettTantargy**

*Feladat:*
* A Tantárgy az ősosztály, leszármaztatott belőle a másik kettő
* Megadják az alapvető objektumstruktúrát a programban 

*Osztályváltozók:*
* Tantargy: tantárgy neve, kreditek száma, fájlnevek (public static final)
* FelvehetoTantargy: előfeltételes-e a tantárgy (true ha igen), előfeltétel neve
* TeljesitettTantargy: osztályzat (enum alapján), félév amiben teljesítve lett

*Metódusok:*
* Konstruktorok
* Getter metódusok
* Mentes (generikus metódus)
* toString

**OsztalyzatEnum**
* A TeljesítettTantárgy osztály egyik tulajdonsága a tárgyra kapott jegy, ez maga az enum, az értéke pedig az osztályzat (1,2,3,4,5)

**GetterFunctionName**

A generikus XML mentésben segédkező osztály

**Methods**

*Feladat:*
* Elvégzi a különböző logikai műveleteket a programban

*Metódusok:*
* felvehetoKiiratas, felvettKiiratas, teljesitettKiiratas
    * A dinamikus listák alapján kiiratja a képernyőre a tartalmukat!
    * Felvehető tantárgyak között azok a tantárgyak szerepelnek amiket még nem teljesített legalább elégségesre a felhasználó, vagy az adott évben még nem vette fel
* tantargyFelvetel
    * A felvehető tantárgyak közül felveszi a felhasználó a megfelelő tantárgyat, amennyiben teljesítette annak előfeltételét már
    * Tantárgy felvétele a megfelelő listába, mentése a felvett tantárgyak XML-jébe
* tantargyLeadas
    * Felvett tantárgyak közül törölhet a felhasználó
    * Tantárgy törlése a listából és az XML fájlból
* felevLepes
    * Az összes felvett tantárgy esetében megkérdezi a felhasználót, hogy milyen jeggyel teljesítette azt
    * Feljebb lépteti a felhasználó jelenlegi félévét
    * Kiüríti a felvett tantárgyakat
    * A teljesített tantárgyak listába és xml-be elmenti az összes érdemjegyet
* kiszamito
    * Félév kiválasztásával kiszámítja és kiírja a hozzá tartozó súlyozott átlagot és KKI-t


**Menu**

*Feladat:*
* Felhasználóval való kapcsolattartás, user interface kezelése

*Metódusok:*
* startup
    *Üdvözli a felhasználót
    *A felhasználó eldöntheti, hogy új, vagy már meglévő adatokat szeretne használni
* mainMenu
    *Kilistázza az összes menüpontot a felhasználó számára
    *Bekéri a felhasználó választását
* listazas
    *Kilistázza a felvehető, felvett, vagy a teljesített tantárgyakat a felhasználó választása szerint
* tantargyFelvetel
    *Meghívja a Methods osztály tantargyFelvetel metódusát
* tantargyLeadas
    *Meghívja a Methods osztály tantargyLeadas metódusát
* felevLepes
    *Meghívja a Methods osztály felevLepes metódusát
* kiszamito
    *Meghívja a Methods osztály kiszamito metódusát



**Fio**

*Feladat:*
* Input, output műveletek (mentés, beolvasás, stb) végrehajtása

*Metódusok:*
* beolvasasFelveheto
     * Beolvassa a felvehetoTargyak.xml fájlból az abban tárolt felvehető tárgyakat és adataikat, majd azokat egy FelvehetoTantargy objektumokból álló dinamikus listába pakolja
* beolvasasTeljesitett
     * Beolvassa a teljesitettTargyak.xml fájlból az abban tárolt eddig teljesített tárgyakat és adataikat, majd azokat egy TeljesitettTantargy objektumokból álló dinamikus listába pakolja
* beolvasFelvett
     * Beolvassa a felvettTargyak.xml fájlból az abban tárolt aktuálisan felvett tárgyakat és adataikat, majd azokat egy Tantargy objektumokból álló dinamikus listába pakolja
* mentes
    * Generikusan menti a megfelelő fájlba az adott objektumot, amelyre meghívják a metódust 
* felevBeolvasas
    * Beolvassa az felev.xml fájlból az éppen aktuális félév értékét majd egy Integer változóban tárolja
* felevMentes
    * Ha változás történik az aktuális félévben, akkor ezt a változást a felev.xml fájlban is rögzíti
* xmlTorles
    * Amikor tiszta lappal kíván indulni a felhasználó az első félévtől, akkor meghívódik ez a metódus majd törli a felvettTargyak.xml és a teljesítettTargyak.xml tartalmát

**Main**


XML fájlok
------

**felev.xml**

* felev - a fájl gyökere és egyetlen eleme, az aktuális félév számát tartalmazza

**felvehetoTargyak.xml**

A szak által felvehető tantárgyakat és adatait tárolja a FelvehetoTantargy osztály tulajdonságai alapján

* tantargyak - a fájl gyökere, tartalmazza a különböző tantárgyakat
* tantargy - egy tantárgy adatait tartalmazza
* nev - az adott tantárgy nevét tartalmazza
* kredit - az adott tantárgy teljesítéséért járó kreditek számát tartalmazza
* elofelteteles - az adott tantárgy előfeltételességét tartalmazza, ha a tárgynak van előfeltétele az értéke “true”, ha nincs, “false”
* elofeltetel - az adott tantárgy előfeltételét tartalmazza, ha nincs, akkor az értéke “NINCS”

**felvettTargyak.xml**

A hallgató által felvett és még nem teljesített tantárgyak adatait tartalmazza a Tantargy osztály tulajdonságai alapján

* tantargyak - a fájl gyökere, tartalmazza a különböző tantárgyakat
* tantargy - a felvett tantárgy adatait tartalmazza
* nev - a felvett tantárgy nevét tartalmazza
* kredit -a felvett tantárgy teljesítéséért járó kreditek számát tartalmazza

**teljesitettTargyak.xml**

A hallgató által teljesített tantárgyak adatait tartalmazza a TeljesitettTantargy osztály tulajdonságai alapján

* tantargyak - a fájl gyökere, tartalmazza a különböző tantárgyakat
* tantargy -  a teljesített tantárgy adatait tartalmazza
* nev - a teljesített tantárgy nevét tartalmazza
* kredit - a teljesített tantárgyért járó kreditek számát tartalmazza
* osztalyzat - a  teljesített tantárgyért járó osztályzatot tartalmazza
* felev - annak a félévnek a számát tartalmazza amelyben az adott tantárgyat teljesítette a hallgató
