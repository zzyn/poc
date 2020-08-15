using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Windows.Forms.Design;
using System.Drawing;

namespace Common.BaseControl
{
    class TabPanelDesigner : ScrollableControlDesigner
    {

        protected override void OnMouseDragBegin(int x, int y)
        {
            base.OnMouseDragBegin(x, y);


            TabPanel tab = base.Component as TabPanel;
            if (tab != null)
            {
                if (tab.IsTab)
                {
                    Point p = new Point(x, y);

                    p = tab.PointToClient(p);



                    Control c = tab.TabControlFromPos(p);
                    if (c != null)
                    {
                        tab.SelectedTabControl = c;
                    }
                }
            }

        }
    }
}
