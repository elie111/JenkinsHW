 <h1 align="center">Fursa HW3 Jenkins Closing Task</h3>
  <h1 align="center">Elie Haddad</h3>
  
<p align="center" >
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Jenkins_logo.svg/1200px-Jenkins_logo.svg.png" alt="drawing" style="width:150px;"/>
</p>

### **Breaking News Ynet Web Application**
#### **To run the project:**
>1. clone the project :
git clone https://github.com/elie111/JenkinsHW.git

> 2. build the project:
mvn clean install assembly:single

> 3. run the jar file:
java -jar target/simplehttpserver-1.0-SNAPSHOT-jar-with-dependencies.jar


### **java application**
*   First thing, with the help of some java libraries like java.net.URL we read the xml page from this [URL](http://www.ynet.co.il/Integration/StoryRss2.xml) which had the breaking news from Ynet, then using the XSLT language we managed to translate the XML to an HTML file so we can read the news more easily, here is an example of the html page we got:

<p align="center" >
  <img src="/Images/newshtml.png" alt="drawing" style="width:700px;"/>
</p>

As you can see, we managed to run the html file on localhost:8080, for that we used an open-source http server code, you can find the original repository [here](https://github.com/CoderFromScratch/simple-java-http-server).


### **Jenkins pipeline**

* we created a jenkins declerative pipeline to automate the building and deployment process, we used a worker node to run the [script](https://github.com/elie111/JenkinsHW/blob/main/Jenkinsfile).
  and we connected the github account to jenkins, and added a Jenkinsfile to our repository and ran the script with the "pipeline script from SCM" option.

<p align="center" >
  <img src="/Images/workernode.png" alt="drawing" style="width:700px;"/>
</p>

<p align="center" >
  <img src="/Images/blueocean.png" alt="drawing" style="width:700px;"/>
</p>

  #### **Clone Stage**:
  > we cloned the project: git branch: "main",url:'https://github.com/elie111/JenkinsHW.git'
  #### **Build Stage**:
  > we built the project using the command: mvn clean install assembly:single 
  #### **Artifact Stage**:
  > we archived the jar file: archiveArtifacts artifacts: '**/*.jar'
  #### **Deployment Stage**:
  >  we ran the jar file that was created from the building stage, which ran the server in the background: 
  > java -jar target/simplehttpserver-1.0-SNAPSHOT-jar-with-dependencies.jar & 
  #### **Slack Notification Stage**: 
  > And finally we sent a message on a slack channel we created to inform us that the project has been built and deployed

<p align="center" >
  <img src="/Images/slack.png" alt="drawing" style="width:700px;"/>
</p>

<p align="center" >
  <img src="/Images/output.png" alt="drawing" style="width:700px;"/>
</p>
