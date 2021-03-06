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
package storm.starter.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

import twitter4j.Status;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.lang.Iterable;
import java.util.Arrays;

public class FilePrinterBolt extends BaseBasicBolt {
    static int numberOfPrint = 0;
    String _fileToWrite;
    OutputStream o;


    public void setPrintFile(String FileToPrint){
        _fileToWrite = FileToPrint;
    }

    public void printNumOfPrint(){
        System.out.println("[REPORT] number of print" + numberOfPrint);
    }

    public int getNumOfPrint(){
        return numberOfPrint;
    }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    System.out.println(tuple);
    //
    Status stat = (Status)tuple.getValue(0);
    String str = stat.getText();
    if( stat.getLang().equals("en"))
        return;

    try{
        o = new FileOutputStream(_fileToWrite, true);
        o.write(str.getBytes());
        o.close();
    }catch (IOException e){
        e.printStackTrace();
    }

    numberOfPrint++;
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer ofd) {
  }

}
