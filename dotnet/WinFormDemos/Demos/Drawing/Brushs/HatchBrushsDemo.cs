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
    public partial class HatchBrushes : Form
    {
        public HatchBrushes()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void HatchBrushes_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            int nWidth = 200;
            int nHeight = 20;
            int nLeft = this.ClientRectangle.Left;
            int nTop = this.ClientRectangle.Top;

            System.Drawing.Font fft = new System.Drawing.Font(FontFamily.GenericSansSerif, 12, GraphicsUnit.Pixel);

            //BackwardDiagonal
            g.DrawString("BackwardDiagonal", fft, Brushes.Black, nLeft, nTop);
            g.FillRectangle(new HatchBrush(HatchStyle.BackwardDiagonal, Color.Black, Color.White), nLeft, nTop + nHeight, nWidth, nHeight);

            //Cross
            g.DrawString("Cross", fft, Brushes.Black, nLeft + nWidth, nTop);
            g.FillRectangle(new HatchBrush(HatchStyle.Cross, Color.Black, Color.White), nLeft + nWidth, nTop + nHeight, nWidth, nHeight);

            //DarkDownwardDiagonal
            g.DrawString("DarkDownwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 2, nTop);
            g.FillRectangle(new HatchBrush(HatchStyle.DarkDownwardDiagonal, Color.Black, Color.White), nLeft + nWidth*2, nTop + nHeight, nWidth, nHeight);

            //DarkHorizontal
            g.DrawString("DarkHorizontal", fft, Brushes.Black, nLeft + nWidth * 3, nTop);
            g.FillRectangle(new HatchBrush(HatchStyle.DarkHorizontal, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight, nWidth, nHeight);

            //DarkUpwardDiagonal
            g.DrawString("DarkUpwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 4, nTop);
            g.FillRectangle(new HatchBrush(HatchStyle.DarkUpwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight, nWidth, nHeight);

            //DarkVertical
            g.DrawString("DarkVertical", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 2);
            g.FillRectangle(new HatchBrush(HatchStyle.DarkVertical, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 3, nWidth, nHeight);

            //DashedDownwardDiagonal
            g.DrawString("DashedDownwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 2);
            g.FillRectangle(new HatchBrush(HatchStyle.DashedDownwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 3, nWidth, nHeight);

            //DashedHorizontal
            g.DrawString("DashedHorizontal", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 2);
            g.FillRectangle(new HatchBrush(HatchStyle.DashedHorizontal, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 3, nWidth, nHeight);

            //DashedUpwardDiagonal
            g.DrawString("DashedUpwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 2);
            g.FillRectangle(new HatchBrush(HatchStyle.DashedUpwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 3, nWidth, nHeight);

            //DashedVertical
            g.DrawString("DashedVertical", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 2);
            g.FillRectangle(new HatchBrush(HatchStyle.DashedVertical, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 3, nWidth, nHeight);

            //DiagonalBrick
            g.DrawString("DiagonalBrick", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 4);
            g.FillRectangle(new HatchBrush(HatchStyle.DiagonalBrick, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 5, nWidth, nHeight);

            //DiagonalCross
            g.DrawString("DiagonalCross", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 4);
            g.FillRectangle(new HatchBrush(HatchStyle.DiagonalCross, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 5, nWidth, nHeight);

            //Divot
            g.DrawString("Divot", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 4);
            g.FillRectangle(new HatchBrush(HatchStyle.Divot, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 5, nWidth, nHeight);

            //DottedDiamond
            g.DrawString("DottedDiamond", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 4);
            g.FillRectangle(new HatchBrush(HatchStyle.DottedDiamond, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 5, nWidth, nHeight);

            //DottedGrid
            g.DrawString("DottedGrid", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 4);
            g.FillRectangle(new HatchBrush(HatchStyle.DottedGrid, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 5, nWidth, nHeight);

            //ForwardDiagonal
            g.DrawString("ForwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 6);
            g.FillRectangle(new HatchBrush(HatchStyle.ForwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 7, nWidth, nHeight);

            //Horizontal
            g.DrawString("Horizontal", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 6);
            g.FillRectangle(new HatchBrush(HatchStyle.Horizontal, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 7, nWidth, nHeight);

            //HorizontalBrick
            g.DrawString("HorizontalBrick", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 6);
            g.FillRectangle(new HatchBrush(HatchStyle.HorizontalBrick, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 7, nWidth, nHeight);

            //LargeCheckerBoard
            g.DrawString("LargeCheckerBoard", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 6);
            g.FillRectangle(new HatchBrush(HatchStyle.LargeCheckerBoard, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 7, nWidth, nHeight);

            //LargeConfetti
            g.DrawString("LargeConfetti", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 6);
            g.FillRectangle(new HatchBrush(HatchStyle.LargeConfetti, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 7, nWidth, nHeight);

            //LargeGrid
            g.DrawString("LargeGrid", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 8);
            g.FillRectangle(new HatchBrush(HatchStyle.LargeGrid, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 9, nWidth, nHeight);

            //LightDownwardDiagonal
            g.DrawString("LightDownwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 8);
            g.FillRectangle(new HatchBrush(HatchStyle.LightDownwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 9, nWidth, nHeight);

            //LightHorizontal
            g.DrawString("LightHorizontal", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 8);
            g.FillRectangle(new HatchBrush(HatchStyle.LightHorizontal, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 9, nWidth, nHeight);

            //LightUpwardDiagonal
            g.DrawString("LightUpwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 8);
            g.FillRectangle(new HatchBrush(HatchStyle.LightUpwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 9, nWidth, nHeight);

            //LightVertical
            g.DrawString("LightVertical", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 8);
            g.FillRectangle(new HatchBrush(HatchStyle.LightVertical, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 9, nWidth, nHeight);

            //Max
            g.DrawString("Max", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 10);
            g.FillRectangle(new HatchBrush(HatchStyle.Max, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 11, nWidth, nHeight);

            //Min
            g.DrawString("Min", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 10);
            g.FillRectangle(new HatchBrush(HatchStyle.Min, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 11, nWidth, nHeight);

            //NarrowHorizontal
            g.DrawString("NarrowHorizontal", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 10);
            g.FillRectangle(new HatchBrush(HatchStyle.NarrowHorizontal, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 11, nWidth, nHeight);

            //NarrowVertical
            g.DrawString("NarrowVertical", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 10);
            g.FillRectangle(new HatchBrush(HatchStyle.NarrowVertical, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 11, nWidth, nHeight);

            //OutlinedDiamond
            g.DrawString("OutlinedDiamond", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 10);
            g.FillRectangle(new HatchBrush(HatchStyle.OutlinedDiamond, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 11, nWidth, nHeight);

            //Percent05
            g.DrawString("Percent05", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 12);
            g.FillRectangle(new HatchBrush(HatchStyle.Percent05, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 13, nWidth, nHeight);

            //Percent25
            g.DrawString("Percent25", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 12);
            g.FillRectangle(new HatchBrush(HatchStyle.Percent25, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 13, nWidth, nHeight);

            //Percent50
            g.DrawString("Percent50", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 12);
            g.FillRectangle(new HatchBrush(HatchStyle.Percent50, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 13, nWidth, nHeight);

            //Percent75
            g.DrawString("Percent75", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 12);
            g.FillRectangle(new HatchBrush(HatchStyle.Percent75, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 13, nWidth, nHeight);

            //Percent90
            g.DrawString("Percent90", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 12);
            g.FillRectangle(new HatchBrush(HatchStyle.Percent90, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 13, nWidth, nHeight);

            //Plaid
            g.DrawString("Plaid", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 14);
            g.FillRectangle(new HatchBrush(HatchStyle.Plaid, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 15, nWidth, nHeight);

            //Shingle
            g.DrawString("Shingle", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 14);
            g.FillRectangle(new HatchBrush(HatchStyle.Shingle, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 15, nWidth, nHeight);

            //SmallCheckerBoard
            g.DrawString("SmallCheckerBoard", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 14);
            g.FillRectangle(new HatchBrush(HatchStyle.SmallCheckerBoard, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 15, nWidth, nHeight);

            //SmallConfetti
            g.DrawString("SmallConfetti", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 14);
            g.FillRectangle(new HatchBrush(HatchStyle.SmallConfetti, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 15, nWidth, nHeight);

            //SmallGrid
            g.DrawString("SmallGrid", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 14);
            g.FillRectangle(new HatchBrush(HatchStyle.SmallGrid, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 15, nWidth, nHeight);

            //SolidDiamond
            g.DrawString("SolidDiamond", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 16);
            g.FillRectangle(new HatchBrush(HatchStyle.SolidDiamond, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 17, nWidth, nHeight);

            //Sphere
            g.DrawString("Sphere", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 16);
            g.FillRectangle(new HatchBrush(HatchStyle.Sphere, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 17, nWidth, nHeight);

            //Trellis
            g.DrawString("Trellis", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 16);
            g.FillRectangle(new HatchBrush(HatchStyle.Trellis, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 17, nWidth, nHeight);

            //Vertical
            g.DrawString("Vertical", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 16);
            g.FillRectangle(new HatchBrush(HatchStyle.Vertical, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 17, nWidth, nHeight);

            //Wave
            g.DrawString("Wave", fft, Brushes.Black, nLeft + nWidth * 4, nTop + nHeight * 16);
            g.FillRectangle(new HatchBrush(HatchStyle.Wave, Color.Black, Color.White), nLeft + nWidth * 4, nTop + nHeight * 17, nWidth, nHeight);

            //Weave
            g.DrawString("Weave", fft, Brushes.Black, nLeft + nWidth * 0, nTop + nHeight * 18);
            g.FillRectangle(new HatchBrush(HatchStyle.Weave, Color.Black, Color.White), nLeft + nWidth * 0, nTop + nHeight * 19, nWidth, nHeight);

            //WideDownwardDiagonal
            g.DrawString("WideDownwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 1, nTop + nHeight * 18);
            g.FillRectangle(new HatchBrush(HatchStyle.WideDownwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 1, nTop + nHeight * 19, nWidth, nHeight);

            //WideUpwardDiagonal
            g.DrawString("WideUpwardDiagonal", fft, Brushes.Black, nLeft + nWidth * 2, nTop + nHeight * 18);
            g.FillRectangle(new HatchBrush(HatchStyle.WideUpwardDiagonal, Color.Black, Color.White), nLeft + nWidth * 2, nTop + nHeight * 19, nWidth, nHeight);

            //ZigZag
            g.DrawString("ZigZag", fft, Brushes.Black, nLeft + nWidth * 3, nTop + nHeight * 18);
            g.FillRectangle(new HatchBrush(HatchStyle.ZigZag, Color.Black, Color.White), nLeft + nWidth * 3, nTop + nHeight * 19, nWidth, nHeight);


        }
    }
}
