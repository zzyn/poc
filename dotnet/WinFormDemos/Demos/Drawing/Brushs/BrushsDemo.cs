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
    public partial class BrushsDemo : Form
    {
        public BrushsDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void BrushsDemo_Paint(object sender, PaintEventArgs e)
        {
            int nWidth = this.Width;
            int nHigh = 50;
            Graphics g = e.Graphics;
            
            //SolidBrush
            g.FillRectangle(new SolidBrush(Color.Purple), new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top, nWidth, nHigh));
            //TextureBrush
           
            g.FillRectangle(new TextureBrush(Demos.Properties.Resources.CCAppWiz_win32.ToBitmap()), new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh, nWidth, nHigh));
            //HatchBrush
            g.FillRectangle(new HatchBrush(HatchStyle.Divot, Color.Red, Color.Green), new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh*2, nWidth, nHigh));
            //LinearGradientBrush
            g.FillRectangle(new LinearGradientBrush(new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh * 3, nWidth, nHigh), Color.DarkBlue, Color.White, 45.0f), new Rectangle(this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh * 3, nWidth, nHigh));
            
            //PathGradientBrush
            Point[] triPoints = new Point[]{ 
                new Point(this.ClientRectangle.Left + nWidth/2, this.ClientRectangle.Top + nHigh *4)
                ,new Point(this.ClientRectangle.Left , this.ClientRectangle.Top + nHigh *5)
                ,new Point(this.ClientRectangle.Left + nWidth, this.ClientRectangle.Top + nHigh *5)
            };
            g.FillRectangle(new PathGradientBrush(triPoints), this.ClientRectangle.Left, this.ClientRectangle.Top + nHigh *4, nWidth, nHigh);
        
        }
    }
}
