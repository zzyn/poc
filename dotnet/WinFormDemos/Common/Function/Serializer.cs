using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Drawing;
using System.Drawing.Imaging;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO.Compression;

namespace Common
{
    
    public class Serializer
    {
        
        public static byte[] BinarySerialize(object obj)
        {
            if (obj == null)
            {
                throw new ArgumentNullException("object is null");
            }

            using (MemoryStream stream = new MemoryStream())
            {
                BinaryFormatter bf = new BinaryFormatter();
                bf.Serialize(stream, obj);
                stream.Close();
                bf = null;
                return stream.ToArray();
            }
           
        }

        public static T BinaryDeserialize<T>(byte[] data)
        {
            if (data == null)
            {
                return default(T);
            }

            using (MemoryStream stream = new MemoryStream(data))
            {
                BinaryFormatter bf = new BinaryFormatter();
                object objReturn = bf.Deserialize(stream);
                stream.Close();
                return (T)objReturn;
            }
            
        }

        public byte[] ImageToByteArray(Image imageIn)
        {
            using (MemoryStream ms = new MemoryStream())
            {
                imageIn.Save(ms, ImageFormat.Gif);
                return ms.ToArray();
            }
        }

        public static Image ByteArrayToImage(byte[] byteArrayIn)
        {
           
            using (MemoryStream ms = new MemoryStream(byteArrayIn))
            using (Image imgSrc = Image.FromStream(ms))
            {
                return new Bitmap(imgSrc);
            }
        }

        public static byte[] StreamCompress(byte[] bData)
        {
            
            using (MemoryStream msData = new MemoryStream())
            {
                using (GZipStream zipStream = new GZipStream(msData, CompressionMode.Compress))
                {
                    zipStream.Write(bData, 0, bData.Length);
                    zipStream.Close();
                    return msData.ToArray();
                }
            }
            
        }

        
        public static byte[] StreamDecompress(byte[] bData)
        {
            
            using (MemoryStream msData = new MemoryStream())
            {
                msData.Write(bData, 0, bData.Length);
                msData.Position = 0;
                using (GZipStream zipStream = new GZipStream(msData, CompressionMode.Decompress))
                using (MemoryStream msTemp = new MemoryStream())
                {
                    byte[] bBuffer = new byte[1024];
                    while (true)
                    {
                        int nRead = zipStream.Read(bBuffer, 0, bBuffer.Length);
                        if (nRead <= 0)
                        {
                            break;
                        }
                        else
                        {
                            msTemp.Write(bBuffer, 0, bBuffer.Length);
                        }
                    }
                    zipStream.Close();
                    return msTemp.ToArray();
                }
            }
            
        }
    }
}
