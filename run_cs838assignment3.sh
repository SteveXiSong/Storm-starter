#!/bin/bash
KEYWORDS="local "\
    #idea yummy death wow wonder Hillary Xi interesting shit lol fifa \
    #nba dota game president ceo cfo cto north korea war south korea japan \
    #india pakistan fire bomb love modi google \
    #now on it you me i at the have had has one two three four five ten hundred \
    #best worst happiest will shall music famous would never ever forever too \
    #which that who \
    #in at on \
    #just like dislike \
    #player singer chief soldier student teacher professor staff scientist hr \
    #sick
    #"

#JOB=storm.starter.cs838_A3_A1
JOB=storm.starter.cs838_A3_A1

storm jar ./target/storm-starter-0.9.5-jar-with-dependencies.jar \
    storm.starter.cs838_A3_A1 \
    ${KEYWORDS}
    #coUny8vLRMgduXbxh4ViyY5j3 \
    #mkXVD2hAsAHJWMlCtKdmhAOfuxV9m1qxBTTlONrVhmEEGRhoKC \
    #330324306-Bbf3Xhaj4iLeTAPBsdK5Di6He0VvfR7Q1Ajshrf5 \
    #QeWIKDmpsKQzN0bkcGOkuVoqQA4JiBIsQggz2olHIoh66 \

