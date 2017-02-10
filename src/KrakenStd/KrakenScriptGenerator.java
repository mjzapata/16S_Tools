package KrakenStd; /**
 * Modified from https://github.com/afodor/clusterstuff/blob/master/src/kw_china_wgs_kraken/StandardKrakenScripts.java
 * Created by mjzapata on 12/6/2016.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class KrakenScriptGenerator {
        public static String MAIN_DIR   = "/nobackup/afodor_research/mjzapata/";
        public static String KRAKEN_DIR = "/nobackup/afodor_research/kwinglee/software/kraken/";
        public static String DB = KRAKEN_DIR + "krakenStandardDB";
        public static String FASTA_DIR = MAIN_DIR + "16S_Studies/fastqs/";
        public static String SCRIPT_DIR = MAIN_DIR + "16S_Studies/krakenScripts/";
        public static String OUT_DIR = MAIN_DIR + "16S_Studies/stdkrakenResults/";

        public static void main(String[] args) throws IOException {

            BufferedWriter script = new BufferedWriter(new FileWriter(new File(
                    SCRIPT_DIR + "runStandardKraken")));
            script.write("#PBS -l walltime=600:00:00\n");
            String[] fastqs = new File(FASTA_DIR).list();
            for(String fa : fastqs) {
                if(fa.endsWith(".fastq")) {
                    String name = fa.replace("_seqs.fastq", "");
                    String seqName = OUT_DIR + "stdkrakenSeqs_" + name;

                    //run kraken
                    script.write(KRAKEN_DIR + "kraken --preload --db "
                            + DB + " --fastq-input " +
                            FASTA_DIR + fa + " --threads 2 > " + seqName + "\n");
                    //translate output
                    script.write(KRAKEN_DIR + "kraken-translate --db "
                            + DB + " " + seqName + " > " + seqName + "_translate\n");
                    script.write(KRAKEN_DIR + "kraken-translate --mpa-format --db "
                            + DB + " " + seqName + " > " + seqName + "_mpa\n");
                }
            }

            script.close();
        }

    }



