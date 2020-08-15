using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;

namespace Demos
{
    public partial class DrawingToImageDemo : Form
    {
        public DrawingToImageDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Rectangle rect = this.ClientRectangle;

            using (Graphics g = this.CreateGraphics()) 
            {
                string filePath = Environment.CurrentDirectory + @"\image.png";
                using (Image img = new Bitmap(rect.Width, rect.Height, g)) 
                {
                    using (Graphics gImage = Graphics.FromImage(img)) 
                    {

                        Bitmap icon = Demos.Properties.Resources.Disable;
                        
                        ColorMap[] colorMap = new ColorMap[1];
                        colorMap[0] = new ColorMap();
                        colorMap[0].OldColor = Color.White;
                        colorMap[0].NewColor = Color.Transparent;

                        ImageAttributes attr = new ImageAttributes();
                        attr.SetRemapTable(colorMap);

                        Bitmap test = Demos.Properties.Resources.CodeBlock.ToBitmap();

                        gImage.DrawImage(test, new Rectangle(0, 0, 16, 16));

                        gImage.DrawImage(icon, rect, 0, 0, rect.Width, rect.Height, GraphicsUnit.Pixel, attr);
 
                        //gImage.FillRectangle(Brushes.Black, rect);
                        //gImage.DrawString("Drawing to an image ...",this.Font,Brushes.White,20,50);
                        try
                        {
                            System.IO.File.Delete(filePath);
                        }
                        catch
                        {

                        }

                       
                        img.Save(filePath);
                        

                    }

                    
                }
                g.DrawImage(Bitmap.FromFile(filePath), new Point(0, 0));
            }
        }
    }
}
