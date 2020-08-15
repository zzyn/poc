using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data;
using System.Drawing;

namespace Common.CustomControl
{
    
    public class CmTreeView : TreeView
    {
        #region Fields
        //private IDictionary<TreeNode, DataRow> dicNodeData;
        private IDictionary<TreeNode, Rectangle> dicNodeRectangle;
        private IDictionary<TreeNode, bool> dicNodeImgFirst;
        private IDictionary<TreeNode, Image> dicNodeImage;
        #endregion

        #region Properties
        
        //public DataRow this[TreeNode objKey]
        //{
        //    get
        //    {
        //        DataRow drValue = null;
        //        dicNodeData.TryGetValue(objKey, out drValue);
        //        return drValue;
        //    }
        //    set
        //    {
        //        if (dicNodeData.Keys.Contains(objKey))
        //        {
        //            dicNodeData.Remove(objKey);
        //        }
        //        dicNodeData.Add(new KeyValuePair<TreeNode, DataRow>(objKey, value));
        //    }
        //}

        public Image this[TreeNode objKey] 
        {
            get
            {
                Image drValue = null;
                dicNodeImage.TryGetValue(objKey, out drValue);
                return drValue;
            }
            set
            {
                if (dicNodeImage.Keys.Contains(objKey))
                {
                    dicNodeImage.Remove(objKey);
                }
                dicNodeImage.Add(new KeyValuePair<TreeNode, Image>(objKey, value));
            }
        }
        #endregion

        #region Constructor
        
        public CmTreeView()
        {
            //dicNodeData = new Dictionary<TreeNode, DataRow>();
            dicNodeImgFirst = new Dictionary<TreeNode, bool>();
            dicNodeRectangle = new Dictionary<TreeNode, Rectangle>();
            dicNodeImage = new Dictionary<TreeNode, Image>();
        }
        #endregion

        #region Methods

        #region Public Methods
        
        //public void AddNode(TreeNode objParent, string strNodeText, DataRow drValue, Color objForceColor, Color objBackColor, bool bImageFirst)
        //{
        //    AddNode(objParent, strNodeText, drValue, string.Empty, objForceColor, objBackColor, bImageFirst);
        //}

        //public void AddNode(TreeNode objParent, string strNodeText, DataRow drValue, string strImageKey, Color objForceColor, Color objBackColor, bool bImageFirst)
        //{
        //    int nIndex = 0;
        //    if (null != objParent)
        //    {
        //        nIndex = objParent.Nodes.Count;
        //    }
        //    else
        //    {
        //        nIndex = this.Nodes.Count;
        //    }
        //    AddNode(objParent, strNodeText, drValue, strImageKey, nIndex, objForceColor, objBackColor, bImageFirst);
        //}

        //public void AddNode(TreeNode objParent, string strNodeText, DataRow drValue, string strImageKey, int nIndex, Color objForceColor, Color objBackColor, bool bImageFirst)
        //{
        //    TreeNode objNode = new TreeNode(strNodeText);
        //    objNode.Name = strNodeText;

        //    if (Color.Empty != objForceColor)
        //    {
        //        objNode.ForeColor = objForceColor;
        //    }
        //    if (Color.Empty != objBackColor)
        //    {
        //        objNode.BackColor = objBackColor;
        //    }
        //    if ((null != this.ImageList) && (0 < this.ImageList.Images.Count) && (string.Empty != strImageKey))
        //    {
        //        objNode.ImageKey = strImageKey;
        //    }
        //    if (null != objParent)
        //    {
        //        objParent.Nodes.Insert(nIndex, objNode);
        //    }
        //    else
        //    {
        //        this.Nodes.Insert(nIndex, objNode);
        //    }
        //    dicNodeImgFirst[objNode] = bImageFirst;
        //    this[objNode] = drValue;
        //}

        public void AddNode(TreeNode objParent, string strNodeText, Image imgNode, string strImageKey, int nIndex, Color objForceColor, Color objBackColor, bool bImageFirst) 
        {
            TreeNode objNode = new TreeNode(strNodeText);
            objNode.Name = strNodeText;

            

            if (Color.Empty != objForceColor)
            {
                objNode.ForeColor = objForceColor;
            }
            if (Color.Empty != objBackColor)
            {
                objNode.BackColor = objBackColor;
            }
            if ((null != this.ImageList) && (0 < this.ImageList.Images.Count) && (string.Empty != strImageKey))
            {
                objNode.ImageKey = strImageKey;
            }
            
            if (null != objParent)
            {
                objParent.Nodes.Insert(nIndex, objNode);
            }
            else
            {
                this.Nodes.Insert(nIndex, objNode);
            }
            dicNodeImgFirst[objNode] = bImageFirst;
            this[objNode] = imgNode;
        }

        public TreeNode[] GetNodeByText(string strNodeText, bool bSearchAllChildren)
        {
            return GetNodeByText(null, strNodeText, bSearchAllChildren);
        }

        public TreeNode[] GetNodeByText(TreeNode objParent, string strNodeText, bool bSearchAllChildren)
        {
            if (null == objParent)
            {
                return this.Nodes.Find(strNodeText, bSearchAllChildren);
            }
            else
            {
                return objParent.Nodes.Find(strNodeText, bSearchAllChildren);
            }
        }
        #endregion

        #region Protected Methods
        
        protected override void OnDrawNode(DrawTreeNodeEventArgs e)
        {
            base.OnDrawNode(e);
            if (DrawMode == TreeViewDrawMode.OwnerDrawAll)
            {

                Color c = (e.Node.BackColor != Color.Empty) ? e.Node.BackColor : ((e.Node.Index % 2 == 0) ? Color.FromArgb(240, 240, 0) : Color.White);
                if ((e.State & TreeNodeStates.Focused) != 0)
                {
                    c = BlendColor(c, Color.Black, 80);
                }
                Brush b = new SolidBrush(c);
                e.Graphics.FillRectangle(b, e.Bounds);

                Rectangle r = e.Bounds;
                r.X += e.Node.Level * 20 + 20;

                if (string.Empty != e.Node.ImageKey)
                {
                    int nImageIndex = this.ImageList.Images.IndexOfKey(e.Node.ImageKey);
                    if (null != ImageList && -1 != nImageIndex)
                    {
                        if (!dicNodeImgFirst[e.Node])
                        {
                            ImageList.Draw(e.Graphics, new Point(ClientSize.Width - ImageList.ImageSize.Width - 8, r.Y + r.Height / 2 - ImageList.ImageSize.Height / 2),
                                nImageIndex);
                        }
                        else
                        {
                            ImageList.Draw(e.Graphics, new Point(r.X, r.Y + r.Height / 2 - ImageList.ImageSize.Height / 2),
                            nImageIndex);
                            r.X += ImageList.ImageSize.Width + 8;
                        }
                    }
                }
                Color fc = (e.Node.ForeColor != Color.Empty) ? e.Node.ForeColor : ForeColor;
                TextRenderer.DrawText(e.Graphics, e.Node.Text, Font, r, fc, Color.Transparent,
                    TextFormatFlags.VerticalCenter | TextFormatFlags.SingleLine | TextFormatFlags.EndEllipsis);

                if (e.Node.Nodes.Count > 0)
                {
                    int nWidth = 8;
                    Rectangle objRec = new Rectangle(r.X - nWidth * 2, (r.Y + (r.Height - nWidth) / 2), nWidth, nWidth);
                    if (dicNodeRectangle.ContainsKey(e.Node))
                    {
                        dicNodeRectangle.Remove(e.Node);
                    }
                    dicNodeRectangle.Add(e.Node, objRec);
                    e.Graphics.FillRectangle(Brushes.White, objRec);
                    int nOffSetx = objRec.Width / 2;
                    int nOffSety = objRec.Height / 2;
                    Color objRecColor = e.Node.BackColor != Color.Empty ? e.Node.BackColor : Color.White;
                    Pen objPen = new Pen(objRecColor);
                    if (!e.Node.IsExpanded)
                    {
                        e.Graphics.DrawLine(objPen, new Point(objRec.Left + nOffSetx, objRec.Top + 2), new Point(objRec.Left + nOffSetx, objRec.Bottom - 2));
                        e.Graphics.DrawLine(objPen, new Point(objRec.Left + 2, objRec.Top + nOffSety), new Point(objRec.Right - 2, objRec.Top + nOffSety));
                    }
                    else
                    {
                        e.Graphics.DrawLine(objPen, new Point(objRec.Left + 2, objRec.Top + nOffSety), new Point(objRec.Right - 2, objRec.Top + nOffSety));
                    }
                    objPen.Dispose();
                }
            }
        }
        
        protected override void OnClick(EventArgs e)
        {
            base.OnClick(e);

            Rectangle objRec;
            Point objPoin = this.PointToClient(Control.MousePosition);
            TreeNode objNode = this.GetNodeAt(objPoin);
            if (null != objNode && this.dicNodeRectangle.TryGetValue(objNode, out objRec))
            {
                if (objRec.Contains(objPoin))
                {
                    if (objNode.IsExpanded)
                    {
                        objNode.Collapse();
                    }
                    else
                    {
                        objNode.Expand();
                    }
                }
            }
        }
       
        protected override void OnMouseDown(MouseEventArgs e)
        {
            TreeNode objNode = this.GetNodeAt(e.Location);
            if (null != objNode)
            {
                this.SelectedNode = objNode;
            }
            base.OnMouseDown(e);
        }
        
        protected override void OnFontChanged(EventArgs e)
        {
            base.OnFontChanged(e);
        }
        
        public static Color BlendColor(Color colorA, Color colorB, int colorApercent)
        {
            int a = (colorA.A * colorApercent + colorB.A * (100 - colorApercent)) / 100;
            int r = (colorA.R * colorApercent + colorB.R * (100 - colorApercent)) / 100;
            int g = (colorA.G * colorApercent + colorB.G * (100 - colorApercent)) / 100;
            int b = (colorA.B * colorApercent + colorB.B * (100 - colorApercent)) / 100;

            return Color.FromArgb(a, r, g, b);
        }
        #endregion

        #endregion
    }
}
