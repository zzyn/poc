package com.etl.meta.media;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws TikaException, SAXException, IOException {

        String mp3File = "/Users/zack/Downloads/c8ddf0326765e6e79cdb364077213c31.mp3";

        AudioMetaInfo metaInfo = getAudioMeta(mp3File);

        System.out.println(metaInfo);

    }

    private static AudioMetaInfo getAudioMeta(String fullPath) throws IOException, TikaException, SAXException {

        File file = new File(fullPath);

        String ext = Files.getFileExtension(fullPath);

        Long crc32 = FileUtils.checksumCRC32(file);

        AudioMetaInfo metaInfo = new AudioMetaInfo();

        if("mp3".equals(ext)){

            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(file);
            ParseContext pcontext = new ParseContext();

            //Mp3 parser
            Mp3Parser Mp3Parser = new  Mp3Parser();
            Mp3Parser.parse(inputstream, handler, metadata, pcontext);

            //String[] metadataNames = metadata.names();

            metaInfo.setDuration(metadata.get("xmpDM:duration"))
                    .setContentType(metadata.get("Content-Type"))
                    .setSize(file.length());
        }

        return metaInfo;
    }


}
