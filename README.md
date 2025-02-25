# Object-Oriented-Programming2

##  The Boat Club
In short this project is about creating a member registry for a boat club. The application should be runnable as a simple console application with a menu and the information in the registry should be loaded and saved to a file.



## Application Requirements
1. You should be able to create a new member. A member has a name, and an optional email adress.
   1. If the member has an email adress it must be unique (two different members cannot have the same email adress)
   1. When a member is created the member is assigned a new unique random 6 character alpha numeric member id. This id must be unique and no other member in the registry should be able to get the same id.
2. You should be able to list all members.
3. You should be able to select a particular member see the detailed information about the member. You should then be able to:
   1. delete the member
   2. add a new boat. A boat has a name, type (sailboat, motorboat, motorsailer, or canoe). Depending on the type there is different information needed:
      1. Sailboat: lenght in meter, depth in meter.
      2. Motorboat: length in meter, engine power in horse powers.
      3. Motorsailer: length in meter, depth in meter, engine power in horse powers.
      4. Canoe: lenght in meter.
   5. Select a boat and see the detailed information about the boat. You should then be able to:
      1. delete the boat
4. You should be able to quit the application
5. The registry information should be loaded from a file `registry.data` when the application starts.
6. The registry information should be saved to a file `registry.data` when the application exits.
7. There should be at least one class diagram showing the application structure with all classes and correct relations between the classes. You do not need to add every operation in the class diagram.
8. Basic error handling, i.e. it should not crash. No need for user friendly error messages.

### File Loading/Saving Format
The requirement is that the data of the application should be loaded from a file when the application starts, and saved to a file when the application ends. The idea is to load the whole file, convert it to nice to work with objects. When the user quits, the whole file is overwritten with new data. Trying to load/save continously or manipulate the file incrementally will only make things more complicated.

The format of the file is as follows:

```
MEMBER:Member Name:optional email adress:member id
optional (BOAT:boat name:(sailboat:length:depth)|(motorboat:length:engine power)|(motorsailer:length:depth:engine power)|(canoe:length))
```

Note that the data depends on the type of boat as per the requirements.

For example:

```
MEMBER:Margaret Hamilton:margaret@nasa.gov:lkd432
BOAT:apollo1:sailboat:20:4
BOAT:appollo3:motorboat:20:50
MEMBER:Allan Turing:allan@bletchleypark.org.uk:kh654s
BOAT:enigma:canoe:4
MEMBER:Barbara Liskov:barbara@mit.edu:bg54w2
BOAT:substitution:motorsailer:17:4:30
MEMBER:Charles Babbage::8ww3ls
```
Note the options for the different boat types. I.e. Barbara Liskov's boat is 17m long, 4m depth with a 30hp engine. Charles Babbage does not have an email adress, and he has no boats.
