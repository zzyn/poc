namespace BigFrame
{
    partial class TreeMenuForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(TreeMenuForm));
            this.ilMenu = new System.Windows.Forms.ImageList(this.components);
            this.tsToolBar = new System.Windows.Forms.ToolStrip();
            this.tsSkinButton = new System.Windows.Forms.ToolStripButton();
            this.tsFontButton = new System.Windows.Forms.ToolStripButton();
            this.tsZoomInButton = new System.Windows.Forms.ToolStripButton();
            this.toolStripComboBox1 = new System.Windows.Forms.ToolStripComboBox();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.MainDesignablePanel = new Common.BaseControl.DesignablePanel();
            this.tvMenu = new Common.CustomControl.CmTreeView();
            this.tsZoomOutButton = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.toolStripProgressBar1 = new System.Windows.Forms.ToolStripProgressBar();
            this.tsToolBar.SuspendLayout();
            this.MainDesignablePanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // ilMenu
            // 
            this.ilMenu.ColorDepth = System.Windows.Forms.ColorDepth.Depth8Bit;
            this.ilMenu.ImageSize = new System.Drawing.Size(16, 16);
            this.ilMenu.TransparentColor = System.Drawing.Color.Transparent;
            // 
            // tsToolBar
            // 
            this.tsToolBar.BackColor = System.Drawing.SystemColors.Info;
            this.tsToolBar.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsSkinButton,
            this.tsFontButton,
            this.tsZoomOutButton,
            this.tsZoomInButton,
            this.toolStripSeparator2,
            this.toolStripComboBox1,
            this.toolStripSeparator1,
            this.toolStripProgressBar1});
            this.tsToolBar.Location = new System.Drawing.Point(0, 0);
            this.tsToolBar.Name = "tsToolBar";
            this.tsToolBar.Size = new System.Drawing.Size(784, 25);
            this.tsToolBar.TabIndex = 0;
            // 
            // tsSkinButton
            // 
            this.tsSkinButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsSkinButton.Image = ((System.Drawing.Image)(resources.GetObject("tsSkinButton.Image")));
            this.tsSkinButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsSkinButton.Name = "tsSkinButton";
            this.tsSkinButton.Size = new System.Drawing.Size(23, 22);
            this.tsSkinButton.Text = "Skin";
            // 
            // tsFontButton
            // 
            this.tsFontButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsFontButton.Image = ((System.Drawing.Image)(resources.GetObject("tsFontButton.Image")));
            this.tsFontButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsFontButton.Name = "tsFontButton";
            this.tsFontButton.Size = new System.Drawing.Size(23, 22);
            this.tsFontButton.Text = "Font";
            // 
            // tsZoomInButton
            // 
            this.tsZoomInButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsZoomInButton.Image = ((System.Drawing.Image)(resources.GetObject("tsZoomInButton.Image")));
            this.tsZoomInButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsZoomInButton.Name = "tsZoomInButton";
            this.tsZoomInButton.Size = new System.Drawing.Size(23, 22);
            this.tsZoomInButton.Text = "Zoom In";
            // 
            // toolStripComboBox1
            // 
            this.toolStripComboBox1.Name = "toolStripComboBox1";
            this.toolStripComboBox1.Size = new System.Drawing.Size(121, 25);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            // 
            // MainDesignablePanel
            // 
            this.MainDesignablePanel.BackColor = System.Drawing.SystemColors.Control;
            this.MainDesignablePanel.ButtonLabelBackColor = System.Drawing.Color.White;
            this.MainDesignablePanel.ButtonLabelFocusBackColor = System.Drawing.Color.White;
            this.MainDesignablePanel.ButtonLabelForeColor = System.Drawing.Color.White;
            this.MainDesignablePanel.Controls.Add(this.tvMenu);
            this.MainDesignablePanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.MainDesignablePanel.Editable = true;
            this.MainDesignablePanel.ErrorBackColor = System.Drawing.Color.White;
            this.MainDesignablePanel.GotFocusBackColor = System.Drawing.Color.White;
            this.MainDesignablePanel.GotFocusLabelForeColor = System.Drawing.Color.White;
            this.MainDesignablePanel.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.MainDesignablePanel.LabelForeColor = System.Drawing.Color.White;
            this.MainDesignablePanel.Location = new System.Drawing.Point(0, 25);
            this.MainDesignablePanel.Name = "MainDesignablePanel";
            this.MainDesignablePanel.Size = new System.Drawing.Size(784, 537);
            this.MainDesignablePanel.TabIndex = 0;
            this.MainDesignablePanel.WarningBackColor = System.Drawing.Color.White;
            // 
            // tvMenu
            // 
            this.tvMenu.AllowDrop = true;
            this.tvMenu.BackColor = System.Drawing.Color.White;
            this.tvMenu.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.tvMenu.Cursor = System.Windows.Forms.Cursors.Default;
            this.tvMenu.Dock = System.Windows.Forms.DockStyle.Left;
            this.tvMenu.DrawMode = System.Windows.Forms.TreeViewDrawMode.OwnerDrawAll;
            this.tvMenu.ForeColor = System.Drawing.Color.Black;
            this.tvMenu.FullRowSelect = true;
            this.tvMenu.ImageIndex = 0;
            this.tvMenu.ImageList = this.ilMenu;
            this.tvMenu.Indent = 20;
            this.tvMenu.ItemHeight = 30;
            this.tvMenu.Location = new System.Drawing.Point(0, 0);
            this.tvMenu.Margin = new System.Windows.Forms.Padding(0);
            this.tvMenu.Name = "tvMenu";
            this.tvMenu.RightToLeftLayout = true;
            this.tvMenu.SelectedImageIndex = 0;
            this.tvMenu.ShowNodeToolTips = true;
            this.tvMenu.ShowPlusMinus = false;
            this.tvMenu.ShowRootLines = false;
            this.tvMenu.Size = new System.Drawing.Size(300, 537);
            this.tvMenu.TabIndex = 0;
            this.tvMenu.NodeMouseDoubleClick += new System.Windows.Forms.TreeNodeMouseClickEventHandler(this.OnTreeNodeMouseDoubleClick);
            // 
            // tsZoomOutButton
            // 
            this.tsZoomOutButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsZoomOutButton.Image = ((System.Drawing.Image)(resources.GetObject("tsZoomOutButton.Image")));
            this.tsZoomOutButton.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsZoomOutButton.Name = "tsZoomOutButton";
            this.tsZoomOutButton.Size = new System.Drawing.Size(23, 22);
            this.tsZoomOutButton.Text = "Zoom Out";
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
            // 
            // toolStripProgressBar1
            // 
            this.toolStripProgressBar1.Alignment = System.Windows.Forms.ToolStripItemAlignment.Right;
            this.toolStripProgressBar1.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.toolStripProgressBar1.Name = "toolStripProgressBar1";
            this.toolStripProgressBar1.Size = new System.Drawing.Size(150, 19);
            // 
            // TreeMenuForm
            // 
            this.ClientSize = new System.Drawing.Size(784, 562);
            this.Controls.Add(this.MainDesignablePanel);
            this.Controls.Add(this.tsToolBar);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "TreeMenuForm";
            this.Text = "Menu";
            this.tsToolBar.ResumeLayout(false);
            this.tsToolBar.PerformLayout();
            this.MainDesignablePanel.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Common.BaseControl.DesignablePanel MainDesignablePanel;
        private System.Windows.Forms.ToolStrip tsToolBar;
        private System.Windows.Forms.ToolStripButton tsSkinButton;
        private System.Windows.Forms.ToolStripButton tsFontButton;
        private System.Windows.Forms.ToolStripButton tsZoomInButton;
        private System.Windows.Forms.ImageList ilMenu;
        private System.Windows.Forms.ToolStripComboBox toolStripComboBox1;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private Common.CustomControl.CmTreeView tvMenu;
        private System.Windows.Forms.ToolStripButton tsZoomOutButton;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripProgressBar toolStripProgressBar1;
    }
}
