# Engineering lead challenge

<details><summary>The issue</summary>
<p>
The employees on this system are assigned to different states, Initially when an employee is added it will be assigned
"ADDED" state automatically .

The other states (State machine) for A given Employee are:
- ADDED
- IN-CHECK
- APPROVED
- ACTIVE

Our backend stack is:
- Java 11
- Spring Framework
- Kafka

**First Part:**

Your task is to build  Restful API doing the following:
- An Endpoint to support adding an employee with very basic employee details including (name, contractDto information,
  age, you can decide.) With initial state "ADDED" which incidates that the employee isn't active yet.
- Another endpoint to change the state of a given employee to "In-CHECK" or any of the states defined above in the
  state machine

Please provide a solution with the  above features with the following consideration.
- Being simply executable with the least effort Ideally using Docker and docker-compose or any smailiar approach.
- For state machine could be as simple as of using ENUM or by using https://projects.spring.io/spring-statemachine/
- Please provide testing for your solution.
- Providing an API Contract e.g. OPENAPI spec. is a big plus

**Second Part (Optional but a plus):**

Being concerned about developing high quality, resilient software, giving the fact, that you will be participating,
mentoring other engineers in the coding review process.
- Suggest what will be your silver bullet, concerns while you're reviewing this part of the software that you need to
  make sure is being there.
- What the production-readiness criteria that you consider for this solution

**Third Part (Optional but a plus):**
Another Team in the company is building another service, This service will be used to provide some statistics of the
employees, this could be used to list the number of employees per country, other types of statistics which is very
vague at the moment
- Please think of a solution without any further implementation that could be able to integrate on top of your service,
  including the integration pattern will be used, the database storage etc.

A high-level architecture diagram is sufficient to present this.
</p>
</details>

## Solution
### [Q]Being simply executable with the least effort Ideally using Docker and docker-compose or any smailiar approach.

For launch the solutions you might use docker, go to the root folder and use the command:

        sudo docker-compose -f devops_tools/docker-compose.yml up -d --build

You may check result, it should looks like:
        
        CONTAINER ID   IMAGE                              COMMAND                  CREATED          STATUS          PORTS                                                                                  NAMES
    ef1dd74f2b84   elc-services:status-api            "/bin/sh -c 'exec ja…"   42 minutes ago   Up 42 minutes   0.0.0.0:8002->8002/tcp, :::8002->8002/tcp                                              devops_tools_status-api_1
    b6ebc8400a1a   elc-services:status-gate           "/bin/sh -c 'exec ja…"   42 minutes ago   Up 42 minutes   0.0.0.0:8001->8001/tcp, :::8001->8001/tcp                                              devops_tools_status-gate_1
    889744c028ce   confluentinc/cp-kafka:latest       "/etc/confluent/dock…"   42 minutes ago   Up 42 minutes   9092/tcp, 0.0.0.0:29092->29092/tcp, :::29092->29092/tcp                                devops_tools_kafka_1
    e40b9845b256   postgres:12-alpine                 "docker-entrypoint.s…"   42 minutes ago   Up 42 minutes   54321/tcp, 127.0.0.1:54321->5432/tcp                                                   user_stock
    0d84ba0b10c7   confluentinc/cp-zookeeper:latest   "/etc/confluent/dock…"   42 minutes ago   Up 42 minutes   2888/tcp, 3888/tcp, 0.0.0.0:22181->2181/tcp, :::22181->2181/tcp                        devops_tools_zookeeper_1

We have here status_gate - is the service which listens all requests and sends to next node, status-api - is the service
which saves our data in database. 

### [Q] For state machine could be as simple as of using ENUM

Еhe state machine was implemented as [enum](status_gate/src/main/java/eu/senla/status_gate/dto/UserState.java). The state 
machine change state according a data for particular fields, if those fields have non `null` value. 

    ADDED
      |
      |_____
            |
      |---->IN_CHECK------>APPROVED------->ACTIVE---->|
      |                                               |
      |_if ACTIVE user gets update, recheck the state_|


### [Q] Please provide testing for your solution.

Unfortunately I have not enough time to make integration of unit tests, but I made Postman collection for UAT testing
[import this collection](postman_uat/status_service.postman_collection.json).

### [Q] Providing an API Contract e.g. OPENAPI spec. is a big plus
We have 2 endpoints:
* First one for creation a user with minimal data, as default state will be ADDED
    ```
    curl --location --request POST 'http://localhost:8001/gate/user' 
  --header 'Content-Type: text/plain' 
  --data-raw'Jonatan Wick 42 '`
    ```
* Second one for an update a data for the user, the state machine change state based on a kind of data
    ```
    curl --location --request PUT 'http://localhost:8001/gate/user' 
  --header 'Content-Type: application/json' 
  --data-raw '{...}'
    ```
Base flow of the service described here below in sequence diagrams

#### Sequence diagram of creation user

                status_gate                    kafka                   status_api
    (Minimal data)    |                          |                          |
    ----------------->|                          |                          |
              build User(ADDED)                  |                          |
    <-----------------|------------------------->|------------------------->|
                      |                          |                      Save user
                      |                          |                          |
                      |                          |                          |


#### Sequence diagram of update user

                status_gate                    kafka                   status_api
    part of data      |                          |                          |
    to upd the user   |                          |                          |
    ----------------->|                          |                          |
              request to original                |                          |
                      |---------------------------------------------------->|
                      |                          |                      Find user
                      |<----------------------------------------------------|
            update data of original              |                          |
            state machne change state            |                          |
    <-----------------|------------------------->|------------------------->|
                      |                          |                     Update user
                      |                          |                          |


### [Q] Suggest what will be your silver bullet, concerns while you're reviewing this part of the software that you need to make sure is being there.

The implementation too fragile because I don't have enough business flow to build state machine easier. In perfect world
we should have one entity for each state, it will help us to keep flexibility of data structures
I don't have minimal layer of testing, as unit testing and integration testing. 

### [Q] What the production-readiness criteria that you consider for this solution

We need to integrate with front-end, security, reorganize data according to real data, correct the business flow.
Lack of general knowledge to discuss production-readiness. The main questions are:
* Business specific requirements
* Security integration
* Data structures
* CI/CD

### [Q] Please think of a solution without any further implementation that could be able to integrate on top of your service,including the integration pattern will be used, the database storage etc.

We use kafka as async message que because we may listen all passed messages for gathering 
statistics or metrics (which might be easily integrated as external solution).
