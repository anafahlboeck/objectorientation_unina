<h1>Scrivimi! - Note Management Application</h1>

<h3>What is Scrivime?</h3>
<p>Welcome to our Notebook Application 'Scrivime', a collaborative 
project developed for the Object-Oriented Programming course at Federico II. 
The primary goal of this application is to provide users with 
a secure environment for managing their notes. Developed using 
Java and JavaFX, the application allows users to log in, create, 
view, sort notes by date, and delete them.</p>

<h3>Make sure the following software is installed:</h3>
<ul>
<li>Docker</li>
<li>Java Development Kit (JDK) 17.0.2</li>
<li>Maven</li>
</ul>

<h3>How to start the Application</h3>
<p>Start the PostgreSQL database with Docker Compose:</p>

``
$ docker compose up
``



<p>You can use the following command for a clean build and run:</p>
<p>Windows:</p>

``
$ .\mvnw.cmd clean javafx:run
``

<p>MacOS:</p>

``
$ mvn clean javafx:run
``

