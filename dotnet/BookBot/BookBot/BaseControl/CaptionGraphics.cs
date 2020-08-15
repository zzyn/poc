using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Drawing2D;

namespace Common.BaseControl
{
    public class CaptionGraphics
    {

        public static void FillRoundRect(Graphics graphics, Brush brush, Rectangle rect, int hradius, int vradius)
        {
            GraphicsPath gp = CreateRoundRectPath(rect, hradius, vradius);
            graphics.FillPath(brush, gp);
        }

        public static void DrawRoundRect(Graphics graphics, Pen pen, Rectangle rect, int hradius, int vradius)
        {
            GraphicsPath gp = CreateRoundRectPath(rect, hradius, vradius);
            graphics.DrawPath(pen, gp);
        }

        public static GraphicsPath CreateRoundRectPath(Rectangle rect, int hradius, int vradius)
        {
            int hr = hradius;
            int vr = vradius;
            if (vr * 2 > rect.Height)
            {
                vr = rect.Height / 2;
            }
            if (hr * 2 > rect.Width)
            {
                hr = rect.Width / 2;
            }
            if (hr <= 0 || vr <= 0)
            {
                GraphicsPath gp = new GraphicsPath();
                gp.AddRectangle(rect);
                return gp;
            }
            else
            {

                GraphicsPath gp = new GraphicsPath();
                gp.AddArc(rect.Left, rect.Top, hr * 2, vr * 2, 180, 90);
                gp.AddArc(rect.Right - hr * 2, rect.Top, hr * 2, vr * 2, 270, 90);
                gp.AddArc(rect.Right - hr * 2, rect.Bottom - vr * 2, hr * 2, vr * 2, 0, 90);
                gp.AddArc(rect.Left, rect.Bottom - vr * 2, hr * 2, vr * 2, 90, 90);
                gp.CloseFigure();

                return gp;
            }
        }

        public static GraphicsPath CreateRoundRectPath(Rectangle rect, int hradius, int vradius, bool lefttop, bool righttop, bool leftbottom, bool rightbottom)
        {
            int hr = hradius;
            int vr = vradius;
            if (vr * 2 > rect.Height)
            {
                vr = rect.Height / 2;
            }
            if (hr * 2 > rect.Width)
            {
                hr = rect.Width / 2;
            }
            if (hr <= 0 || vr <= 0)
            {
                GraphicsPath gp = new GraphicsPath();
                gp.AddRectangle(rect);
                return gp;
            }
            else
            {

                GraphicsPath gp = new GraphicsPath();
                if (lefttop)
                {
                    gp.AddArc(rect.Left, rect.Top, hr * 2, vr * 2, 180, 90);
                }
                else
                {
                    gp.AddLine(rect.Location, rect.Location);
                }
                if (righttop)
                {
                    gp.AddArc(rect.Right - hr * 2, rect.Top, hr * 2, vr * 2, 270, 90);
                }
                else
                {
                    gp.AddLine(new Point(rect.Right, rect.Top), new Point(rect.Right, rect.Top));
                }
                if (rightbottom)
                {
                    gp.AddArc(rect.Right - hr * 2, rect.Bottom - vr * 2, hr * 2, vr * 2, 0, 90);
                }
                else
                {
                    gp.AddLine(new Point(rect.Right, rect.Bottom), new Point(rect.Right, rect.Bottom));
                }
                if (leftbottom)
                {
                    gp.AddArc(rect.Left, rect.Bottom - vr * 2, hr * 2, vr * 2, 90, 90);
                }
                else
                {
                    gp.AddLine(new Point(rect.Left, rect.Bottom), new Point(rect.Left, rect.Bottom));
                }
                gp.CloseFigure();

                return gp;
            }
        }

        public static Color BlendColor(Color colorA, Color colorB, int colorApercent)
        {
            int a = (colorA.A * colorApercent + colorB.A * (100 - colorApercent)) / 100;
            int r = (colorA.R * colorApercent + colorB.R * (100 - colorApercent)) / 100;
            int g = (colorA.G * colorApercent + colorB.G * (100 - colorApercent)) / 100;
            int b = (colorA.B * colorApercent + colorB.B * (100 - colorApercent)) / 100;

            return Color.FromArgb(a, r, g, b);
        }

        public static int ColorLength(Color colorA, Color colorB)
        {
            int a = Math.Abs(colorA.A - colorB.A);
            int r = Math.Abs(colorA.R - colorB.R);
            int g = Math.Abs(colorA.G - colorB.G);
            int b = Math.Abs(colorA.B - colorB.B);

            return a + r + g + b;

        }

        public static void DrawShadow(Graphics graphics, GraphicsPath gp)
        {
            DrawShadow(graphics, gp, 1, 4, 30);
        }

        public static void DrawShadow(Graphics graphics, GraphicsPath gp, float shift, float width, int depth)
        {
            Matrix m = new Matrix();
            m.Translate(shift, shift);
            gp.Transform(m);

            using (Pen p = new Pen(Color.FromArgb(depth, 0, 0, 0), width))
            {
                graphics.DrawPath(p, gp);
                p.Width = width / 2;
                graphics.DrawPath(p, gp);
            }

            m.Translate(-shift * 2, -shift * 2);
            gp.Transform(m);

            m.Dispose();
            m = null;
        }

        public static void DrawLight(Graphics graphics, GraphicsPath gp, Color c, float width, int depth)
        {

            Pen p = new Pen(Color.FromArgb(depth, c), width);
            graphics.DrawPath(p, gp);
            p.Width = width / 2;
            graphics.DrawPath(p, gp);

        }

    }
}
