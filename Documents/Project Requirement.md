# Braille Project Requirements Document

## Table of Contents

1. [Introduction](#1-introduction)
    - 1.1 [Purpose of this document](#11-purpose-of-this-document) 
    - 1.2 [Scope](#12-scope)
    - 1.3 [Definitions, acronyms, and abbreviations](#13-definitions,-acronyms,-and-abbreviations)
    - 1.4 [References](#14-references)
2. [Overall description](#2-overall-description)
      - 2.1 [Product Perspective](#21-product-perspective)
      - 2.2 [User Characteristics](#22-user-characteristics)
      - 2.3 [Constraints](#23-constraints)
3. [Specific requirements](#3-specific-requirements)
      - 3.1 [External Interface Requirements](#31-external-interface-requirements)   
      - 3.1.1 [User Interface](#311-user-interface)
      - 3.2 [Functional Requirements](#32-functional-requirement)   
      - 3.2.1 [The User](#321-the-user) 
      - 3.3 [Performance Requirements](#33-performance-requirements)
     
4. [Use cases](#4-use-cases)
5. [Test cases](#5-test-cases)

## 1. Introduction
This section explains the purpose and scope of Braille program as well deifintions, acronyms, and abbreviations with its refereces.

The document was shaped in the format similar to [this](#14-references)[1].

### 1.1 Purpose of this document
The following document is created to:
1. explain what the project and the Braille program is for
2. track down the requirements set by customers
3. track down the references used 
4. show the use case of the Braille program
5. show test cases of the Braille program
6. get an approval to Braille program project

### 1.2 Scope
The "Braille Project" is a visually-impaired educator assistant application which provide an easy way for the educators to create **scenarios** to provide an entertaining braille learning experience to the students the educators are teaching.

This program will provide the educators tools to import, export, create, and edit the **scenario** and import custom voice lines. 

### 1.3 Definitions, acronyms, and abbreviations
| Term  | Definition |
| ------  | ------ | 
| scenario | A custom made case study for the users to create to provide an entertaining educational experience to Braille learners. The scenario file is ritten in a script format.|
| Desc | Description |
| Dep | Dependency |
| AF | Alternative Flow |
| EX | Exception |

### 1.4 References
[1] Geagea, S., Zhang, S., Shalin, N., Hasibi, F., Hameed, F., Rafiyan, E. and Ekberg, M. (2018). Software Requirements Sepcification. [ebook] chalmers, p.2. Available at: http://www.cse.chalmers.se/~feldt/courses/reqeng/examples/srs_example_2010_group2.pdf [Accessed 4 Feb. 2018].
## 2. Overall description
The following section will give a breakdown of the project. The breakdown will explain different the interactions between classes and functions internally. The basic functionality will be explained, and a description of user and implementer interaction will be explored. Finally, the constraints and assumptions for the project will be presented. 

### 2.1 Product Perspective
A shallow description of the project can be stated by a device that allows kids, including visually impaired kids, to learn how to read braille. The device will display characters/words to the user who then respond to the question by pressing buttons. The main system is a software to help educators to create these scenarios and questions.

Essentially, the main system is an authoring app that must be usable by visually impaired users. The authoring app will provide facilities and functions to create the flow of the scenario where the user can ask questions and receive answers. A function for the user (educator) to record, save, and upload the audio will be given. Furthermore, there will be a facility implemented to save the scenario in an appropriate format and then test the scenario using provided software.

### 2.2 User Characteristics
There are essentially 2 types of users that can use the application: those visually impaired, and those who are not. Each of these users cannot navigate through the application in the same way as one another and the application must be compatible for both types. A screen reader for the visually impaired will be provided for them to use the authoring app. 



### 2.3 Constraints


## 3. Specific requirements
This sections contains, highlights, and explains all the functional and quality requirements of the system.

### 3.1 External Interface Requirements
#### 3.1.1 User Interfaces   
Esentially there are two types of users who will be interacting with the graphical interace: educator, and a visually impaired educator. The interface must be implemented in such a way that both users find ease in navigating through the app. For visually imapaired users, a screen reader function will be implemented that will allow them to map out different buttons and fields of the app.

There will be four main windows of the app:    
1. The main menu: Here the user can enter the scenario editor or exit the program.    
2. The scenario editor: In this window the user will have be able to view the scenario list and have access to: create a new scenario, edit selected scenario, save seected scenario, load scenario, and run selected scenario functions.   
3. The scenario maker: This window will open up once the user selcts "create a new scenario." In this window, the user can create their own scenario and edit existing scenarios as well. The user can set the file name, the number of pins and buttons, and have the ability to create and remove commands and save the file.  
4. Run scenario: When the user selects a scenario to run, a window will open up that uses the provided tetsing software to run the scenario they have created.  

### 3.2 Functional Requirements

#### 3.2.1 The User

**_3.2.1.1 Functional Requirement 1.1_**  
**ID: FR1**  
**Title:** Create a new sceario(s)  
**Desc:** The user should be able to create and implement their own scenario(s).  
**Dep:** None  

**_3.2.1.2 Functional Requirement 1.2_**  
**ID: FR2**  
**Title:** Save created scenario(s)   
**Desc:** The user should be able to save their created scenario in the scenario list or as a file with specified format.    
**Dep:** FR1  

**_3.2.1.3 Functional Requirement 1.3_**  
**ID: FR3**  
**Title:** Edit Existing Scenarios    
**Desc:** The user should be able to select and edit an existing scenario from the scenario list.     
**Dep:** There should be existing scenarios.  


**_3.2.1.4 Functional Requirement 1.4_**  
**ID: FR4**  
**Title:**  Load Scenarios     
**Desc:** The user should be allowed to navigate through their directory and load selected scenario to add to scenario list.       
**Dep:** ?  

**_3.2.1.5 Functional Requirement 1.5_**  
**ID: FR5**  
**Title:** Run Scenarios      
**Desc:** The user should be able to select a scenario and run it.  
**Dep:** ? 

**_3.2.1.6 Functional Requirement 1.6_**  
**ID: FR6**  
**Title:** Record user audio       
**Desc:** The user should be able to record their own audio in an appropriate file format. Also, the user should be able to utilize the screen reader if they do not want to record their own audio.  
**Dep:** Screen reader 

**_3.2.1.7 Functional Requirement 1.7_**  
**ID: FR7**  
**Title:** Screen Reader      
**Desc:** For the users that are visually impaired, the feature of a screen reader should me implemented and can be accessed in a easy way.   
**Dep:** Type of user  


### 3.3 Performance Requirements
This requirement section outlines the performance specifications that the user can expect from the software.  
#### 3.3.1 Easy Navigation to Scenario Editor  
**_ID:_** QR1  
**_Title:_** Easy Navigation to editor   
**_Desc:_** The user should be able to easily find and navigate to the scenario editor easily from the main menu.   
**_Rat:_** In order for the user to easily access the editor window. 


#### 3.3.2 Usage of Scenario Lists 
**_ID:_** QR2   
**_Title:_** Usage of the scenario list   
**_Desc:_** The scenarios displayed in scenario list should be outputed in a user friendly way and easy to understand. Selecting a scenario from the list should only take 1 click.   
**_Rat:_** In order for the user to use the list view easily. 

#### 3.3.3 Usage of Scenario Maker 
**_ID:_** QR3   
**_Title:_** Usage of the scenario maker    
**_Desc:_** The functions and features of the scenario maker should have a user-freindly layout that is easy to navigate and use to conduct various tasks (i.e create command, remove command, save, etc). Features and functions should not be hard to find.  
**_Rat:_** In order for the user to use the scenario maker easily. 

#### 3.3.4 Pre-existing scenario deliverabilities
**_ID:_** QR4   
**_Title:_** Usage of the scenario editor to modify scenarios.     
**_Desc:_** Pre-existing scenarios that have been created or loaded should be displayed in a user friendly way in the scenario list and the features to modify the scenarios should be easily accessible and easy to find.  
**_Rat:_** In order for the user to modify scenarios.   

#### 3.3.5 Usage of Audio Recording
**_ID:_** QR5    
**_Title:_** Usage of the audio recording.       
**_Desc:_** The steps taken to record user's audio should be simple to use and easy to save upon recoding completion.     
**_Rat:_** In order for the user to easily record their audio.  

#### 3.3.6 Usage of Audio Recording
**_ID:_** QR6     
**_Title:_** Usage of the screen reader      
**_Desc:_** The visually impaired users should be able to navigate and turn of the screen reader feature easily and without any hassle. Also, the screen reader should allow the user to naviagte through the authoring app without any difficulties.       
**_Rat:_** In order for visually impaired users to use the app. 


## 4. Use cases  
This section will outline the different use cases and outline requirements specification that will capture how user(s) will intteract with the authoring app and acieve a specific goal. The section will describe a step by step process a user will go through to complete and accomplish a task or goal.

### 4.1: User 1-Educator  
#### 4.1.1 User Case 1  
**Name:** Make a scenario  
**Brief Description:**  The user wants to create a  scenario.  
**Actor:**  Educator  
**Preconditions:**  User has entered the scenario editor from the main menu.  
**Basic Flow:**  
     1. User selects the "Create a new scenario" button     
     2. System redirects the user to the scenario maker window    
     3. User enters the name of this new scenario and selects whether it will be saved in the scenario list and/or another file                  location   
     4. User enters the number of braille cells and the number of buttons for this scenario.    
     5. User can begin making a scenario suing the create/remove command options and place the commands above, below, or replace them.  
     6. Once scenario is complete, user can save the scenario and/or choose to exit and be redirected to the scenario editor page.   
    
**Alternative Flow:**   
**AF1:** User wants to edit an existing saved scenario in the scenario list.     
      1. User selects the scenario from the scenario list and clicks "Edit Selected Scenario"
      2. System redirects to the scenario maker window
      3. Return user to basic flow step 4    
**AF2:** User wants to upload a scenario from their directory and edit that  
      1. User selects "Load Scenario" and finds the scenario they want to edit in their directory
      2. The selected scenario is displayed in scenario list
      3. User selects the uploaded scenario and and clicks "Edit Selected Scenario"
      4. Return user to basic flow step 4
      
**Exception Flows:**  
**EX1:** System fails in saving scenario to scenario list  
    1. System notifies user that an error has occured
    2. Return user to basic flow step 3
    
**EX2:**  User uploads uncorrect scenario file format/missing file
    1. System reminds user of what files will be accepted/that the file does not exist.  
    2. Return user to AF2 step 1  

**EX3:**  User enters incorrect number of cells or number of buttons  
    1. System notifies user they have entered an inccorect value   
    2. Return system to basic flow step 4     
    
**Post Conditions:**  
The user has created a new/existing file ready to be run.     

#### 4.1.2 User Case 2  
**Name:** Run a scenario     
**Brief Description:**  The user wants to run a  scenario.     
**Actor:**  Educator/tester    
**Preconditions:** Scenario's need to have been already created without any errors, and user has enterred the scenario editor window    
**Basic Flow:**  
    1. User selects a scenario from the scenario list   
    2. User clicks "Run Scenario" button  
    3. System redirects and runs the selected scenario  
    
**Alternative Flow:**  
**AF1:** The user wants to run an uploaded scenario 
    1. User clicks "Load Scenario," system redirects page allowing user to find the scenario file in their directory    
    2. System displays the scenario in the scenario list    
    3. Redirect user to basic flow step 1  
    
**Exception Flows:**   
**EX1:** Scenario file in scenario list or dorectory is not saved in the appropriate format.  
    1. System displays error message informing the user that the system does not support the format  
    2. Redirect user to basic flow step 1.  
    
**Post Conditions:**   
The selected scenario file runs.  


#### 4.1.3 User Case 3  
**Name:** Record Audio 
**Brief Description:**  The user wants to record audio.    
**Actor:**  Educator 
**Preconditions:**    
**Basic Flow:**  
**Alternative Flow:**  
**Exception Flows:**   
**Post Conditions:**   

## 5. Test case  
| Execution Process | Expected Result | Pass or Fail |
| ------  | ------ | ------ | 
| Create Scenario is pressed | A window with the scenario editor opens up | Pass |
| Edit a scenario from scenario list | Selected scenario opens up in scenario editor once edit is pressed | Pass |
| Load a scenario | Window opens up allowing user to select a sceanrio which appears in scenario list | Pass |


