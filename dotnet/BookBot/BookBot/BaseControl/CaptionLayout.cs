using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Windows.Forms.Layout;

namespace Common.BaseControl
{
    public class CaptionLayout : LayoutEngine
    {
        private Size PreLayout(object container, Size proposedSize)
        {
            Control parent = container as Control;

            if (parent != null && parent.IsHandleCreated && !parent.IsDisposed)
            {
                int basewidth = proposedSize.Width;
                ScrollableControl sc = parent as ScrollableControl;
                if (sc != null)
                {
                    if (sc.AutoScroll)
                    {
                        basewidth -= SystemInformation.VerticalScrollBarWidth;
                    }
                }

                int fontheight = parent.Font.Height;

                int dockLeft = parent.Padding.Left;
                int dockRight = parent.Padding.Right;
                int dockTop = parent.Padding.Top;
                int dockBottom = parent.Padding.Bottom;

                bool addControlOrder = false;

                ICaptionPanel cp = container as ICaptionPanel;
                if (cp != null)
                {
                    addControlOrder = cp.AddControlOrder;

                    if (!cp.FolderOpened)
                    {
                        return new Size(cp.CaptionPadding.Left + cp.CaptionPadding.Right, cp.CaptionPadding.Top);
                    }

                    dockLeft += cp.CaptionPadding.Left;
                    dockRight += cp.CaptionPadding.Right;
                    dockTop += cp.CaptionPadding.Top;
                    dockBottom += cp.CaptionPadding.Bottom;

                }

                int sizeHeight = 0;
                int sizeWidth = 0;

                for (int i = 0; i < parent.Controls.Count; i++)
                {

                    Control ctrl = (addControlOrder) ? parent.Controls[i] : parent.Controls[parent.Controls.Count - 1 - i];

                    if (!ctrl.Visible)
                    {
                        continue;
                    }

                    Size propSize = ((basewidth - dockLeft - dockRight - ctrl.Margin.Horizontal) > 0) ? new Size(basewidth - dockLeft - dockRight - ctrl.Margin.Horizontal, 0) : Size.Empty;

                    switch (ctrl.Dock)
                    {
                        case DockStyle.Fill:
                            {
                                Size sz = (ctrl.AutoSize) ? ctrl.GetPreferredSize(propSize) : new Size(fontheight, fontheight);
                                sizeWidth = (int)Math.Max(sizeWidth, sz.Width + dockLeft + dockRight + ctrl.Margin.Horizontal);
                                sizeHeight = (int)Math.Max(sizeHeight, sz.Height + dockTop + dockBottom + ctrl.Margin.Vertical);
                            }
                            break;
                        case DockStyle.Top:
                            {
                                Size sz = ctrl.Size;
                                if (ctrl.AutoSize)
                                {
                                    sz = ctrl.GetPreferredSize(propSize);
                                    if (proposedSize.Width == 0)
                                    {
                                        sizeWidth = (int)Math.Max(sizeWidth, sz.Width + dockLeft + dockRight + ctrl.Margin.Horizontal);
                                    }

                                }
                                dockTop += (sz.Height + ctrl.Margin.Vertical);
                            }
                            break;
                        case DockStyle.Bottom:
                            {
                                Size sz = ctrl.Size;
                                if (ctrl.AutoSize)
                                {
                                    sz = ctrl.GetPreferredSize(propSize);
                                    if (proposedSize.Width == 0)
                                    {
                                        sizeWidth = (int)Math.Max(sizeWidth, sz.Width + dockLeft + dockRight + ctrl.Margin.Horizontal);
                                    }

                                }
                                dockBottom += (sz.Height + ctrl.Margin.Vertical);
                            }
                            break;
                        case DockStyle.Left:
                            {
                                Size sz = ctrl.Size;
                                if (ctrl.AutoSize)
                                {
                                    sz = ctrl.GetPreferredSize(Size.Empty);

                                    sizeHeight = (int)Math.Max(sizeHeight, sz.Height + dockTop + dockBottom + ctrl.Margin.Vertical);
                                }

                                dockLeft += (sz.Width + ctrl.Margin.Horizontal);

                            }
                            break;
                        case DockStyle.Right:
                            {
                                Size sz = ctrl.Size;
                                if (ctrl.AutoSize)
                                {
                                    sz = ctrl.GetPreferredSize(Size.Empty);

                                    sizeHeight = (int)Math.Max(sizeHeight, sz.Height + dockTop + dockBottom + ctrl.Margin.Vertical);
                                }
                                dockRight += (sz.Width + ctrl.Margin.Horizontal);
                            }
                            break;
                        case DockStyle.None:
                            {
                                sizeHeight = (int)Math.Max(sizeHeight, ctrl.Top + ctrl.Height + ctrl.Margin.Bottom);
                                sizeWidth = (int)Math.Max(sizeWidth, ctrl.Left + ctrl.Width + ctrl.Margin.Right);
                            }
                            break;

                    }
                }


                Size ps = new Size(Math.Max(sizeWidth, dockLeft + dockRight), Math.Max(dockTop + dockBottom, sizeHeight));

                return ps;

            }

            return Size.Empty;

        }

        public Size GetPreferredSize(object container, Size proposedSize)
        {
            Control parent = container as Control;

            if (parent != null)
            {
                return PreLayout(container, proposedSize);


            }

            return Size.Empty;


        }

        public override void InitLayout(object child, BoundsSpecified specified)
        {

            base.InitLayout(child, specified);

        }

        public override bool Layout(object container, LayoutEventArgs layoutEventArgs)
        {


            Control parent = container as Control;

            if (parent != null && parent.IsHandleCreated && !parent.IsDisposed)
            {

                int basewidth = parent.DisplayRectangle.Width;
                ScrollableControl sc = parent as ScrollableControl;
                if (sc != null)
                {
                    if (sc.AutoScroll)
                    {
                        basewidth = parent.Width - SystemInformation.VerticalScrollBarWidth - 1;
                    }
                }

                int dockLeft = parent.Padding.Left;
                int dockRight = parent.Padding.Right;
                int dockTop = parent.Padding.Top;
                int dockBottom = parent.Padding.Bottom;

                bool addControlOrder = false;


                ICaptionPanel cp = container as ICaptionPanel;
                if (cp != null)
                {
                    addControlOrder = cp.AddControlOrder;

                    if (!cp.FolderOpened)
                    {
                        return parent.AutoSize;
                    }
                    dockLeft += cp.CaptionPadding.Left;
                    dockRight += cp.CaptionPadding.Right;
                    dockTop += cp.CaptionPadding.Top;
                    dockBottom += cp.CaptionPadding.Bottom;
                }

                dockTop += parent.DisplayRectangle.Y;
                dockRight += parent.DisplayRectangle.X;

                int fillWidth = 0;
                int fillHeight = 0;

                DockStyle ds = DockStyle.None;

                for (int i = 0; i < parent.Controls.Count; i++)
                {
                    Control c = (addControlOrder) ? parent.Controls[i] : parent.Controls[parent.Controls.Count - 1 - i];

                    ds = c.Dock;

                    if (!c.Visible)
                    {
                        continue;
                    }
                    Size propSize = ((basewidth - dockLeft - dockRight - c.Margin.Horizontal) > 0) ? new Size(basewidth - dockLeft - dockRight - c.Margin.Horizontal, 0) : Size.Empty;

                    switch (c.Dock)
                    {
                        case DockStyle.Top:
                            {
                                Size sz = (c.AutoSize) ? c.GetPreferredSize(propSize) : c.Size;
                                c.SetBounds(dockLeft + c.Margin.Left, dockTop + c.Margin.Top, propSize.Width, sz.Height);
                                dockTop += sz.Height + c.Margin.Vertical;
                                fillHeight = 0;

                            }
                            break;
                        case DockStyle.Bottom:
                            {
                                Size sz = (c.AutoSize) ? c.GetPreferredSize(propSize) : c.Size;
                                c.SetBounds(dockLeft + c.Margin.Left, parent.Height - dockBottom - sz.Height - c.Margin.Bottom, propSize.Width, sz.Height);
                                dockBottom += sz.Height + c.Margin.Vertical;
                                fillHeight = 0;
                            }
                            break;
                        case DockStyle.Left:
                            {
                                Size sz = (c.AutoSize) ? c.GetPreferredSize(Size.Empty) : c.Size;
                                //Size sz = (c.AutoSize) ? c.GetPreferredSize(new Size(c.Width, 0)) : c.Size;
                                c.SetBounds(dockLeft + c.Margin.Left, dockTop + c.Margin.Top, sz.Width, parent.Height - dockTop - dockBottom - c.Margin.Vertical);
                                dockLeft += sz.Width + c.Margin.Horizontal;
                                fillWidth = 0;
                            }
                            break;
                        case DockStyle.Right:
                            {
                                Size sz = (c.AutoSize) ? c.GetPreferredSize(Size.Empty) : c.Size;
                                //Size sz = (c.AutoSize) ? c.GetPreferredSize(new Size(c.Width, 0)) : c.Size;
                                c.SetBounds(basewidth - dockRight - sz.Width - c.Margin.Right, dockTop + c.Margin.Top, sz.Width, parent.Height - dockTop - dockBottom - c.Margin.Vertical);
                                dockRight += sz.Width + c.Margin.Horizontal;
                                fillWidth = 0;
                            }
                            break;
                        case DockStyle.Fill:
                            {
                                if (c.AutoSize)
                                {
                                    Size sz = c.GetPreferredSize(propSize);

                                    fillWidth = sz.Width;
                                    fillHeight = sz.Height;

                                    sz.Width = Math.Max(sz.Width, basewidth - dockLeft - dockRight - c.Margin.Horizontal);
                                    sz.Height = Math.Max(sz.Height, parent.Height - dockTop - dockBottom - c.Margin.Vertical);


                                    c.SetBounds(dockLeft + c.Margin.Left, dockTop + c.Margin.Top, sz.Width, sz.Height);

                                }
                                else
                                {
                                    Size sz = new Size(basewidth - dockLeft - dockRight - c.Margin.Horizontal, parent.Height - dockTop - dockBottom - c.Margin.Vertical);

                                    fillWidth = sz.Width;
                                    fillHeight = sz.Height;
                                    c.SetBounds(dockLeft + c.Margin.Left, dockTop + c.Margin.Top, sz.Width, sz.Height);


                                }



                            }
                            break;

                    }
                    c = null;

                }

                bool rtn = false;
                if (parent.AutoSize)
                {
                    switch (ds)
                    {
                        case DockStyle.Top:
                        case DockStyle.Bottom:
                            if (dockTop + dockBottom - parent.DisplayRectangle.Y != parent.DisplayRectangle.Height)
                            {
                                rtn = true;
                            }
                            break;
                        case DockStyle.Left:
                        case DockStyle.Right:
                            if (dockLeft + dockRight - parent.DisplayRectangle.X != basewidth)
                            {
                                rtn = true;

                            }
                            break;
                        case DockStyle.Fill:
                            if ((dockLeft + fillWidth + dockRight - parent.DisplayRectangle.X != basewidth) || (dockTop + fillHeight + dockBottom - parent.DisplayRectangle.Y != parent.DisplayRectangle.Height))
                            {
                                rtn = true;
                            }
                            break;
                        case DockStyle.None:
                            rtn = true;
                            break;
                    }
                }

                parent = null;
                sc = null;
                cp = null;

                return rtn;

            }

            return false;
        }

    }
}
