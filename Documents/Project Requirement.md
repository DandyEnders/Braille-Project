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

### 1.4 References
[1] Geagea, S., Zhang, S., Shalin, N., Hasibi, F., Hameed, F., Rafiyan, E. and Ekberg, M. (2018). Software Requirements Sepcification. [ebook] chalmers, p.2. Available at: http://www.cse.chalmers.se/~feldt/courses/reqeng/examples/srs_example_2010_group2.pdf [Accessed 4 Feb. 2018].
## 2. Overall description
The following section will give a breakdown of the project. The breakdown will explain different the interactions between classes and functions internally. The basic functionality will be explained, and a description of user and implementer interaction will be explored. Finally, the constraints and assumptions for the project will be presented. 

### 2.1 Product Perspective
A shallow description of the project can be stated by a device that allows kids, including visually impaired kids, to learn how to read braille. The device will display characters/words to the user who then respond to the question by pressing buttons. The main system is a software to help educators to create these scenarios and questions.

Essentially, the main system is an authoring app that must be usable by visually impaired users. The authoring app will provide facilities and functions to create the flow of the scenario where the user can ask questions and receive answers. A function for the user (educator) to record, save, and upload the audio will be given. Furthermore, there will be a facility implemented to save the scenario in an appropriate format and then test the scenario using provided software.

### 2.2 User Characteristics
There are essentially 2 types of users that can use the application: those visually impaired, and those who are not. Each of these users cannot navigate through the application in the same way as one another and the application must be compatible for both types. 
***INSERT MORE HERE ON HOW WE ARE MAKING THE APP FOR BOTH USERS***


### 2.3 Constraints


## 3. Specific requirements
This sections contains, highlights, and explains all the functional and quality requirements of the system.

### 3.1 External Interface Requirements
#### 3.1.1 User Interfaces

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
**_Desc:_** Pre-existign scenarios that have been created or loaded should be displayed in a user friendly way in the scenario list and the features to modify the scenarios should be easily accessible and easy to find.  
**_Rat:_** In order for the user to modify scenarios.   

#### 3.3.5 Usage of Audio Recording
**_ID:_** QR5    
**_Title:_** Usage of the audio recording.       
**_Desc:_** The steps taken to record user's audio should be simple to use and easy to save upon recoding completion.     
**_Rat:_** In order for the user to easily record their audio.  









## 4. Use cases

## 5. Test cases

