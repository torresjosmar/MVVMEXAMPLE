# POC MVVM app template

Template con la arquitectura inicial para el desarrollo de aplicaciones Android bajo el patron de arquitctura MVVM.


#### La app contiene los siguientes paquetes:
1. **data**: Contiene todas las clases que acceden a la data y manipulacion de componentes.
2. **di**: Contiene todas las dependencias proveidas utilizando Dagger2.
3. **ui**: Contiene solo las clases de cada vista con su correspondiente viewmodel.
4. **utils**: Clases de utilidades.

#### Las clases se han diseñado de tal manera que puedan heredarse y maximizar la reutilización del código.

### Referencias de librerias:
1. RxJava2: https://github.com/ReactiveX/RxJava
2. Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3. Retrofit: https://square.github.io/retrofit/
4. PlaceHolderView: https://github.com/janishar/PlaceHolderView
5. AndroidDebugDatabase: https://github.com/amitshekhariitbhu/Android-Debug-Database
6. Room: https://developer.android.com/topic/libraries/architecture/room.html

### Conceptos de librerias utilizadas:
1. [Introduction to Dagger 2: Part 1](https://blog.mindorks.com/introduction-to-dagger-2-using-dependency-injection-in-android-part-1-223289c2a01b#.ki6nt86l6)
2. [Introduction to Dagger 2: Part 2](https://blog.mindorks.com/introduction-to-dagger-2-using-dependency-injection-in-android-part-2-b55857911bcd#.mkpzyk8sa)
3. [Android Dagger2: Critical things to know before you implement](https://blog.mindorks.com/android-dagger2-critical-things-to-know-before-you-implement-275663aecc3e#.bskiz1879)
4. [The Best Android Networking Library for Fast and Easy Networking](https://blog.mindorks.com/simple-and-fast-android-networking-19ed860d1455#.cyzrve85o)
5. [RxJava + Fast Android Networking](https://blog.mindorks.com/rxjava-fast-android-networking-6e3d90ee4387#.7hjoex22m)
6. [Migrating from RxJava 1.0 to RxJava 2.0 and Learn RxJava by Examples](https://blog.mindorks.com/migrating-from-rxjava1-to-rxjava2-5dac0a94b4aa#.3lg46kora)
7. [Android Tinder Swipe View Example](https://blog.mindorks.com/android-tinder-swipe-view-example-3eca9b0d4794#.u7i7jbbvy)
8. [Debugging Android Databases And Shared Preferences In The Easiest Way](https://blog.mindorks.com/debugging-android-databases-and-shared-preferences-in-the-easiest-way-e5f705dfc06b#.pxw0hvnws)
