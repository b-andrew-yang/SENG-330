# SENG-330
Object Oriented Programming

Introduction to the discipline of object-oriented software design. Topics include encapsulation, polymorphism, inheritance, inversion of control, testing object-oriented code, concurrency, and design patterns. Development of practical skills using modern tools and techniques based on current research and practice in software engineering.

Assignment 1:
  Specifications:
    * Design a 'Player' class, which have some properties you must add:
        - 'Position' on a hocket team. There are at least four: 'Defender, Winger, Centre, Goalie'.
        - 'points' (a point in hockey is a goal or an assist).
        - 'name'.
    * In addition, add methods to create a Player, and add a 'getPoints' and 'getName' method.
    * Implement the class called 'Team' that will use the 'Player" type to maintain a list of players. Use a      
      generic/parameterized type 'List' to hold the players. Teams should store the number of players, and have a name (e.g. 
      Canucks, Penguins, ...)
     * Using JetUML, create a UML Object diagram to represent a 'League' instance. Leagues have multiple 'Teams'.
     * Implement a 'League' object holding the teams, per the object diagram. Leagues should define a 'sort' method that sorts 
      teams by total points.
     * Implement the 'Comparable' interface for the 'Team' class using total 'Player' points. The 'Team' with more player
      points should be ranked higher. Use this to sort the teams in the 'League'.
     * Implement a 'PlayerComparator' class for ranking hockey players based on points. Use this comparator to sort the 
      players in a 'Team' according to points.
     * Override the 'Object.toString()' method in order to print the following for a 'Team':
          'System.out.println(canucks) --> "Canucks have four points."
   
  Lessons Learned:
     * For this assignment we were to use 'Gradle' to build and test our program. Using 'Gradle' we could easily automate
       compile/build/test loops from the command line. This was my first time using 'Gradle' and it was a little bit difficult 
       to get started with it.
     * Inheritance and sub-classes were a big theme of this assignment and it was an introduction to working with objects in 
       this way. Generic classes were introduced to me previous to this assignment but using them in a list in order to sort 
       and rank was different.
     * Lastly, the 'Comparable' interface and implementation of a 'PlayerComparator' were design frameworks that I have never 
       used before. These interfaces made it easy to sort a list, using the inherent 'sort()' methods of lists. 
       
Assignment 2:
  Specifications:
     * Build an 'IOT' (Internet of Things) system with a 'Mediator' that will handle updates from multiple sources and send 
      to multiple recipients in a smart home. You should support these types of IOT devices:
        - 'Camera'
        - 'Thermostat'
        - 'Lightbulb'
        - 'SmartPlug'
        - 'Hub'
     * These devices have the following common behaviors:
        - 'Status getStatus()' - will send messages about status as requested.
        - 'UUID getIdentifier()' - Returns a 'java.util.UUID', a global unique ID for an instance of the device.
     * Each device has its own behavior as well. Throw an appropriate exception if an error might occur (e.g. the Camera is
      full.
        - 'record()' - Camera. If recording, stop, if not recording, start.
        - 'setTemp(Temperature) - Thermostat. 'Temperature' should be a class but keep it simple.
        - 'toggle()' - Smartplug and Lightbulb (on/off). If on, turn off, if off, turn on.
     * The 'Hub' has the following responsibilities:
        - 'log()': all message traffic should be logged using 'SLF4J'. Logging should be parameterized between Error, Warn,
          and info levels. Logs shhould print to the screen where configured to do so, and save data to a log file if that
          option is chosen.
        - 'alert(String message)': receive an update from a device.
        - 'register()' - handle registration from clients and devices (assume security etc is implemented elsewhere). The
          'Hub' will have to maintain a registry of clients and devices.'
        - 'statup()' -- initialize the system.
        - 'shutdown()' -- safely turn off the system.
     * The clients will be notified of new events and get a JSON object. Implement two clients that can select different
      devices to monitor. Clients share the following method:
        - 'notify(org.json.JSONObject pMsg)' - be notified by the Hub of some interesting event. The notification will be a 
          message in JSON - a sample is provided in the bootstrap repo. For example, if a delivery is made to the front door
          , the 'Camera' will 'alert()' the 'Hub', and the 'Hub' will notify (all) clients.
     * Name the clients:
        - AndroidClient
        - WebClient
     * Your system should respect the design goals and privacy, security, and modularity. This will be marked as part of the
      design component. 
     * Your assignment will need to adhere to the Google Java style. We will use Checkstyle to enforce this.
     * Use JUnit to test your assignment. 
  
  Lessons Learned:
     * Using JUnit to test was an interesting thought experiment. I think at times its assumed that code will just execute
      the way we've written it, but it doesn't always. Tests are an important part to ensure that our code in functioning as
      intended.
     * 'SLF4J' to log was also interesting. Previous to using a logging library, logs were just done through print 
      statements.
     * This was a more complicated version of Assignment 1. Using the Hub to access the different 'Things' so inheritance
      was far more important.

Assignment 3:
  Specifications:
    * Implement a "Client" (View) that handles updates from cameras, thermostats etc. This client may be either a
      'WebClient', 'AndroidClient', or 'DesktopClient'.
         - Use JavaFX/FXML or Spring MVC to implement the UI.
    * Implement a 'Model" that persists the various data streams. I suggest you begin with an in-memory Model (i.e., the
      data is only available while the app is running, and then discarded), and then work on storage offline. The
      simplest offline storage is as a serialized (text-file) JSONObject. The assignment 2 'Hub' class is a natural
      choice to start, but you should think about how much responsibility the Hub wil have.
    * Implement 'Controllers'. Controllers allow the Client to request updates, to display status, and to add new Device
      instances.
    * New since assignment 2, you should add 2 classes of users: Admin and Basic. Admins can manage devices (add/remove),
      and manage users (what devices they see). Don't worry about a bullet proof user management componenet. Plain text
      passwords are fine. In reality this would be a terrible idea.
    * Your solution must successfully pass the list of Acceptance/Integration tests posted. These take the form of
        - Given a functioning camera, when the user clicks on the Client camera control (button tab, etc), then they
          see the data from the Camera. 
          
  Lessons Learned:
    * JavaFX had quite a large learning curve for me. I had never dabbled with UI implementation so that was quite
      illuminating. Once I got the hang of it, it became a very enjoyable aspect of the asignment. 
    * MVC (Model, View, Controller) design was tough to coordinate at first. On paper it makes perfect sense, but
      implementation was different. Using this design to implement our 'Hub' forced me to understand why MVC is 
      one of the most used designs in the industry. Having loosely coupled code that only needs to know as much as
      it needs is very useful. Each separate file had increased legibility and less clutter. It also made it clear
      which file needed to be accessed to made changes/fix bugs etc. 
