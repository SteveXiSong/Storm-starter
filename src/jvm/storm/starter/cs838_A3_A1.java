/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package storm.starter;

import java.util.Arrays;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import backtype.storm.StormSubmitter;

import storm.starter.bolt.PrinterBolt;
import storm.starter.bolt.FilePrinterBolt;
import storm.starter.spout.TwitterSampleSpout;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class cs838_A3_A1 {

    static String cKey = "coUny8vLRMgduXbxh4ViyY5j3";
    static String cSecret = "mkXVD2hAsAHJWMlCtKdmhAOfuxV9m1qxBTTlONrVhmEEGRhoKC";
    static String tKey = "330324306-Bbf3Xhaj4iLeTAPBsdK5Di6He0VvfR7Q1Ajshrf5";
    static String tSecret = "QeWIKDmpsKQzN0bkcGOkuVoqQA4JiBIsQggz2olHIoh66";

    static int timeInterval = 10;

    public static void main(String[] args) throws Exception {
        /*
        String consumerKey = args[0];
        String consumerSecret = args[1];
        String accessToken = args[2];
        String accessTokenSecret = args[3];
        */

        String consumerKey = cKey;
        String consumerSecret = cSecret;
        String accessToken = tKey;
        String accessTokenSecret = tSecret;

        String[] arguments = args.clone();

        if( arguments[0].equals("local") && arguments[0].equals("cluster")){
            System.out.println(arguments[0]);
            System.out.println("first arg is \"local\" or \"cluster\" ");
            return;
        }

        String[] keyWords = Arrays.copyOfRange(arguments, 1, arguments.length);

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("twitter", new TwitterSampleSpout(consumerKey, consumerSecret,
                                accessToken, accessTokenSecret, keyWords));

        FilePrinterBolt fpBolt = new FilePrinterBolt();
        //DateFormat df = new SimpleDateFormat("(mm/dd-HH:mm)");
        //Date dateobj = new Date();
        fpBolt.setPrintFile("collectTwitter.txt");

        builder.setBolt("print",fpBolt )
                .shuffleGrouping("twitter");


        Config conf = new Config();

        if(  arguments[0].equals("local")){
            LocalCluster cluster = new LocalCluster();

            cluster.submitTopology("test", conf, builder.createTopology());


            while(true){
                Utils.sleep(timeInterval*1000);
                fpBolt.printNumOfPrint();
                if(fpBolt.getNumOfPrint() > 500000)
                    break;
            }

            cluster.shutdown();
        }
        else if(arguments[0] == "cluster"){
            conf.setNumWorkers(4);

            try{
                StormSubmitter.submitTopologyWithProgressBar(arguments[0], conf, builder.createTopology());
            }catch (Exception e){
                throw e;
            }
        }else{
            System.out.println("Bad arg");
        }

    }
}
