import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class parseDemux16SKraken {

    public static final String QIITAID   = "1189";
    public static final String QIITASAMPLEID = "seqs.labels";
    public static final String SAMPLEDIR = "C:\\Users\\mjzapata\\Data\\16S_Studies\\"+QIITAID+"\\";
    //public static final String METAFILENAME  = SAMPLEDIR + "77_20150818-225203.txt";
    public static final String KRAKENDIROUTPUT = SAMPLEDIR + "output\\";
    //public static final String SAMPLEDIR = "/Users/malcolm/Downloads/16S_Studies/77/";
    //public static final String METAFILENAME  = SAMPLEDIR + "77_20150818-225203.txt";
    //public static final String KRAKENDIROUTPUT = SAMPLEDIR + "output/";
    public static HashMap<String, BufferedWriter> writerMap = new HashMap<String, BufferedWriter>();

    public static void main(String args[]) throws IOException {

                //pull all unique sample names
                //File metafile  = new File(METAFILENAME);
                //read line
                ArrayList<String> samplenameList = new ArrayList<>();
                //HashMap<String, BufferedWriter> writerMap = new HashMap<String, BufferedWriter>();
/*
                try (BufferedReader metabr = new BufferedReader(new FileReader(metafile))){
                    String line;
                    while((line = metabr.readLine()) != null){
                        if(!line.startsWith("sample_name")) {
                            String samplename = line.split("\t")[0];
                            samplenameList.add(samplename);
                            BufferedWriter writer = writerMap.get(samplename);
                            if(writer == null) {
                                writer = new BufferedWriter(new FileWriter(KRAKENDIROUTPUT + samplename + "_mpa");
                                writerMap.put(samplename, writer);
                            }
                            writer.write(line);

                        }
                    }
                }
*/
                //pull all unique taxa at every level?
                //maybe in advance

                String inFileName  = SAMPLEDIR + QIITASAMPLEID;
                //String outFileName = KRAKENDIROUTPUT + "279_seqs_counts.labels";
                File infile = new File(inFileName);
                //File outfile= new File(outFileName);
                //if (!outfile.exists()) {
                  //  outfile.createNewFile();
               // }
                //read line
                try (BufferedReader br = new BufferedReader(new FileReader(infile))) {
                    //BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
                    String line;
                    while ((line = br.readLine()) != null) {
                        //parse line
                        String[] tabParse = line.split("\t");
                        String samplename= tabParse[0].split("_")[0];
                        //String [] taxo = tabParse[1].split("|");
                        //for(int i = 1; i < taxo.length; i++) {
                        //if(taxo[i].startsWith("p__")) {


                        BufferedWriter writer = writerMap.get(samplename);
                        if(writer == null) {
                            writer = new BufferedWriter(new FileWriter(KRAKENDIROUTPUT + samplename + "_mpa"));
                            writerMap.put(samplename, writer);
                        }
                        writer.write(line + "\n");


                        //if(samplename.equals("77.TS21")) {


                           // bw.write(line + "\n");
                        //}
                        //}



                        //}
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }



                closeWriterMap();
            }



    public static void closeWriterMap() {
        Set<String> keySet = writerMap.keySet();
        for(String key : keySet) {
            BufferedWriter writer = writerMap.get(key);
            if(writer != null) {
                try {
                    writer.close();
                }
                catch(Throwable t) {
                    t.printStackTrace();
                }
                writer = null;
            }
        }
    }

            //public static

}