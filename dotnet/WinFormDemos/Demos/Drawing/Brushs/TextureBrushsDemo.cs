using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Drawing2D;

namespace Demos
{
    public partial class TextureBrushes : Form
    {
        public TextureBrushes()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void TextureBrushes_Paint(object sender, PaintEventArgs e)
        {
            int nWidth = this.Width;
            int nHigh = 128;
            Graphics g = e.Graphics;


            Bitmap image = Demos.Properties.Resources.CCAppWiz_win32.ToBitmap(); 
            
            //Clamp
            Brush ClampBrush = new TextureBrush(image, WrapMode.Clamp);
            g.FillRectangle(ClampBrush, new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top, nWidth, nHigh));
            //Tile
            Brush TileBrush = new TextureBrush(image, WrapMode.Tile);
            g.FillRectangle(TileBrush, new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh, nWidth, nHigh));

            //TileFlipX
            Brush TileFlipXBrush = new TextureBrush(image, WrapMode.TileFlipX);
            g.FillRectangle(TileFlipXBrush, new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh*2, nWidth, nHigh));

            //TileFlipY
            Brush TileFlipYBrush = new TextureBrush(image, WrapMode.TileFlipY);
            g.FillRectangle(TileFlipYBrush, new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh * 3, nWidth, nHigh));

            //TileFlipxY
            Brush TileFlipXYBrush = new TextureBrush(image, WrapMode.TileFlipXY);
            g.FillRectangle(TileFlipXYBrush, new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh * 4, nWidth, nHigh*2));
        }
    }
}
