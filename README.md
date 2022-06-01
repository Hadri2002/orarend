Tantárgy kezelő program (Neptun copy)
=========

**A program célja**

A program segítségével a programtervező informatikus hallgatók fel tudják venni tantárgyaikat félévenként, illetve ki tudják számítani az átlagukat, KKI-t.

Package-ek
-----

**FIO**

Ez a package tartalmazza a fájl írással, illetve olvasással kapcsolatos metódusokat.

Osztályok:
* Fio

**Business**

A business package adja meg a program logikai alapját, ez végzi el az összes műveletet

Osztályok:
* FelvehetoTantargy
* TeljesitettTantargy
* OsztalyzatEnum
* GetterFunctionName
* Methods

**Business.os**

A business package-en belüli ős package

Osztályok:
* Tantargy

**Controller**

A User Interface kezelésével foglalkozó package

Osztályok:
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

**Methods**

**Menu**
*Feladat:*
*F
*Metódusok:*
*startup*
*mainMenu*
*listazas*
*tantargyFelvetel*
*tantargyLeadas*
*felevLepes*
*kiszamito*



**Fio**

**Main**


XML fájlok
-------

**felev.xml**
**felvehetoTargyak.xml**
**felvettTargyak.xml**
**teljesitettTargyak.xml**
