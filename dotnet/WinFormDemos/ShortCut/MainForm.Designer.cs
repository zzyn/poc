namespace ShortCut
{
    partial class MainForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.ctrNotifyIcon = new System.Windows.Forms.NotifyIcon(this.components);
            this.ctrNotifyMenuContext = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.tsmiNotifyMenuShow = new System.Windows.Forms.ToolStripMenuItem();
            this.tsNotifyMenuSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiNotifyMenuGlobalSetting = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNotifyMenuButtonSetting = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNotifyMenuSkins = new System.Windows.Forms.ToolStripMenuItem();
            this.tsNotifyMenuSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiNotifyMenuHelp = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNotifyMenuVersion = new System.Windows.Forms.ToolStripMenuItem();
            this.tsNotifyMenuSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiNotifyMenuExit = new System.Windows.Forms.ToolStripMenuItem();
            this.ctrButtonMenuContext = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.tsmiOpenFileLocation = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiFileAttribute = new System.Windows.Forms.ToolStripMenuItem();
            this.tsButtonMenuSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiAddButton = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiEditButton = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDeleteButton = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiOrderButton = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiAutoOrder = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiOrderByName = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiOrderByLocation = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiOrderByUsage = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiOrderByComment = new System.Windows.Forms.ToolStripMenuItem();
            this.tsButtonMenuSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiButtonMenuPage = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNewPage = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiEditPageName = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDeletePage = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiMoveLeft = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiMoveRight = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuMainMenu = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuShow = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiButtonMenuGlobalSetting = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuButtonSetting = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuSkins = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiButtonMenuHelp = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuVersion = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiButtonMenuSeparator5 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiButtonMenuExit = new System.Windows.Forms.ToolStripMenuItem();
            this.pnlContent = new System.Windows.Forms.Panel();
            this.flpContent = new System.Windows.Forms.FlowLayoutPanel();
            this.flpTabs = new System.Windows.Forms.FlowLayoutPanel();
            this.ctrNotifyMenuContext.SuspendLayout();
            this.ctrButtonMenuContext.SuspendLayout();
            this.pnlContent.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlCaption
            // 
            this.pnlCaption.Size = new System.Drawing.Size(400, 25);
            // 
            // ctrNotifyIcon
            // 
            this.ctrNotifyIcon.ContextMenuStrip = this.ctrNotifyMenuContext;
            this.ctrNotifyIcon.Icon = ((System.Drawing.Icon)(resources.GetObject("ctrNotifyIcon.Icon")));
            this.ctrNotifyIcon.Visible = true;
            this.ctrNotifyIcon.Click += new System.EventHandler(this.OnShow);
            // 
            // ctrNotifyMenuContext
            // 
            this.ctrNotifyMenuContext.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiNotifyMenuShow,
            this.tsNotifyMenuSeparator1,
            this.tsmiNotifyMenuGlobalSetting,
            this.tsmiNotifyMenuButtonSetting,
            this.tsmiNotifyMenuSkins,
            this.tsNotifyMenuSeparator2,
            this.tsmiNotifyMenuHelp,
            this.tsmiNotifyMenuVersion,
            this.tsNotifyMenuSeparator3,
            this.tsmiNotifyMenuExit});
            this.ctrNotifyMenuContext.Name = "ctrNotifyMenuContext";
            this.ctrNotifyMenuContext.Size = new System.Drawing.Size(151, 176);
            // 
            // tsmiNotifyMenuShow
            // 
            this.tsmiNotifyMenuShow.Name = "tsmiNotifyMenuShow";
            this.tsmiNotifyMenuShow.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuShow.Text = "Show";
            this.tsmiNotifyMenuShow.Click += new System.EventHandler(this.OnShow);
            // 
            // tsNotifyMenuSeparator1
            // 
            this.tsNotifyMenuSeparator1.Name = "tsNotifyMenuSeparator1";
            this.tsNotifyMenuSeparator1.Size = new System.Drawing.Size(147, 6);
            // 
            // tsmiNotifyMenuGlobalSetting
            // 
            this.tsmiNotifyMenuGlobalSetting.Name = "tsmiNotifyMenuGlobalSetting";
            this.tsmiNotifyMenuGlobalSetting.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuGlobalSetting.Text = "Global Setting";
            // 
            // tsmiNotifyMenuButtonSetting
            // 
            this.tsmiNotifyMenuButtonSetting.Name = "tsmiNotifyMenuButtonSetting";
            this.tsmiNotifyMenuButtonSetting.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuButtonSetting.Text = "Button Setting";
            // 
            // tsmiNotifyMenuSkins
            // 
            this.tsmiNotifyMenuSkins.Name = "tsmiNotifyMenuSkins";
            this.tsmiNotifyMenuSkins.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuSkins.Text = "Skin";
            // 
            // tsNotifyMenuSeparator2
            // 
            this.tsNotifyMenuSeparator2.Name = "tsNotifyMenuSeparator2";
            this.tsNotifyMenuSeparator2.Size = new System.Drawing.Size(147, 6);
            // 
            // tsmiNotifyMenuHelp
            // 
            this.tsmiNotifyMenuHelp.Name = "tsmiNotifyMenuHelp";
            this.tsmiNotifyMenuHelp.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuHelp.Text = "Help";
            // 
            // tsmiNotifyMenuVersion
            // 
            this.tsmiNotifyMenuVersion.Name = "tsmiNotifyMenuVersion";
            this.tsmiNotifyMenuVersion.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuVersion.Text = "Version";
            // 
            // tsNotifyMenuSeparator3
            // 
            this.tsNotifyMenuSeparator3.Name = "tsNotifyMenuSeparator3";
            this.tsNotifyMenuSeparator3.Size = new System.Drawing.Size(147, 6);
            // 
            // tsmiNotifyMenuExit
            // 
            this.tsmiNotifyMenuExit.Name = "tsmiNotifyMenuExit";
            this.tsmiNotifyMenuExit.Size = new System.Drawing.Size(150, 22);
            this.tsmiNotifyMenuExit.Text = "Exit";
            // 
            // ctrButtonMenuContext
            // 
            this.ctrButtonMenuContext.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiOpenFileLocation,
            this.tsmiFileAttribute,
            this.tsButtonMenuSeparator1,
            this.tsmiAddButton,
            this.tsmiEditButton,
            this.tsmiDeleteButton,
            this.tsmiOrderButton,
            this.tsButtonMenuSeparator2,
            this.tsmiButtonMenuPage,
            this.tsmiButtonMenuMainMenu});
            this.ctrButtonMenuContext.Name = "ctrButtonMenuContext";
            this.ctrButtonMenuContext.Size = new System.Drawing.Size(174, 192);
            // 
            // tsmiOpenFileLocation
            // 
            this.tsmiOpenFileLocation.Name = "tsmiOpenFileLocation";
            this.tsmiOpenFileLocation.Size = new System.Drawing.Size(173, 22);
            this.tsmiOpenFileLocation.Text = "Open File Location";
            // 
            // tsmiFileAttribute
            // 
            this.tsmiFileAttribute.Name = "tsmiFileAttribute";
            this.tsmiFileAttribute.Size = new System.Drawing.Size(173, 22);
            this.tsmiFileAttribute.Text = "File Attribute";
            // 
            // tsButtonMenuSeparator1
            // 
            this.tsButtonMenuSeparator1.Name = "tsButtonMenuSeparator1";
            this.tsButtonMenuSeparator1.Size = new System.Drawing.Size(170, 6);
            // 
            // tsmiAddButton
            // 
            this.tsmiAddButton.Name = "tsmiAddButton";
            this.tsmiAddButton.Size = new System.Drawing.Size(173, 22);
            this.tsmiAddButton.Text = "Add Button";
            this.tsmiAddButton.Click += new System.EventHandler(this.OnShowButtonAttributeForm);
            // 
            // tsmiEditButton
            // 
            this.tsmiEditButton.Name = "tsmiEditButton";
            this.tsmiEditButton.Size = new System.Drawing.Size(173, 22);
            this.tsmiEditButton.Text = "Edit Button";
            // 
            // tsmiDeleteButton
            // 
            this.tsmiDeleteButton.Name = "tsmiDeleteButton";
            this.tsmiDeleteButton.Size = new System.Drawing.Size(173, 22);
            this.tsmiDeleteButton.Text = "Delete Button";
            // 
            // tsmiOrderButton
            // 
            this.tsmiOrderButton.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiAutoOrder,
            this.tsmiOrderByName,
            this.tsmiOrderByLocation,
            this.tsmiOrderByUsage,
            this.tsmiOrderByComment});
            this.tsmiOrderButton.Name = "tsmiOrderButton";
            this.tsmiOrderButton.Size = new System.Drawing.Size(173, 22);
            this.tsmiOrderButton.Text = "Order Button";
            // 
            // tsmiAutoOrder
            // 
            this.tsmiAutoOrder.Name = "tsmiAutoOrder";
            this.tsmiAutoOrder.Size = new System.Drawing.Size(144, 22);
            this.tsmiAutoOrder.Text = "Auto";
            // 
            // tsmiOrderByName
            // 
            this.tsmiOrderByName.Name = "tsmiOrderByName";
            this.tsmiOrderByName.Size = new System.Drawing.Size(144, 22);
            this.tsmiOrderByName.Text = "By Name";
            // 
            // tsmiOrderByLocation
            // 
            this.tsmiOrderByLocation.Name = "tsmiOrderByLocation";
            this.tsmiOrderByLocation.Size = new System.Drawing.Size(144, 22);
            this.tsmiOrderByLocation.Text = "By Location";
            // 
            // tsmiOrderByUsage
            // 
            this.tsmiOrderByUsage.Name = "tsmiOrderByUsage";
            this.tsmiOrderByUsage.Size = new System.Drawing.Size(144, 22);
            this.tsmiOrderByUsage.Text = "By Usage";
            // 
            // tsmiOrderByComment
            // 
            this.tsmiOrderByComment.Name = "tsmiOrderByComment";
            this.tsmiOrderByComment.Size = new System.Drawing.Size(144, 22);
            this.tsmiOrderByComment.Text = "By Comment";
            // 
            // tsButtonMenuSeparator2
            // 
            this.tsButtonMenuSeparator2.Name = "tsButtonMenuSeparator2";
            this.tsButtonMenuSeparator2.Size = new System.Drawing.Size(170, 6);
            // 
            // tsmiButtonMenuPage
            // 
            this.tsmiButtonMenuPage.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiNewPage,
            this.tsmiEditPageName,
            this.tsmiDeletePage,
            this.tsmiMoveLeft,
            this.tsmiMoveRight});
            this.tsmiButtonMenuPage.Name = "tsmiButtonMenuPage";
            this.tsmiButtonMenuPage.Size = new System.Drawing.Size(173, 22);
            this.tsmiButtonMenuPage.Text = "Page";
            // 
            // tsmiNewPage
            // 
            this.tsmiNewPage.Name = "tsmiNewPage";
            this.tsmiNewPage.Size = new System.Drawing.Size(158, 22);
            this.tsmiNewPage.Text = "New Page";
            // 
            // tsmiEditPageName
            // 
            this.tsmiEditPageName.Name = "tsmiEditPageName";
            this.tsmiEditPageName.Size = new System.Drawing.Size(158, 22);
            this.tsmiEditPageName.Text = "Edit Page Name";
            // 
            // tsmiDeletePage
            // 
            this.tsmiDeletePage.Name = "tsmiDeletePage";
            this.tsmiDeletePage.Size = new System.Drawing.Size(158, 22);
            this.tsmiDeletePage.Text = "Delete Page";
            // 
            // tsmiMoveLeft
            // 
            this.tsmiMoveLeft.Name = "tsmiMoveLeft";
            this.tsmiMoveLeft.Size = new System.Drawing.Size(158, 22);
            this.tsmiMoveLeft.Text = "Move Left";
            // 
            // tsmiMoveRight
            // 
            this.tsmiMoveRight.Name = "tsmiMoveRight";
            this.tsmiMoveRight.Size = new System.Drawing.Size(158, 22);
            this.tsmiMoveRight.Text = "Move Right";
            // 
            // tsmiButtonMenuMainMenu
            // 
            this.tsmiButtonMenuMainMenu.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiButtonMenuShow,
            this.tsmiButtonMenuSeparator3,
            this.tsmiButtonMenuGlobalSetting,
            this.tsmiButtonMenuButtonSetting,
            this.tsmiButtonMenuSkins,
            this.tsmiButtonMenuSeparator4,
            this.tsmiButtonMenuHelp,
            this.tsmiButtonMenuVersion,
            this.tsmiButtonMenuSeparator5,
            this.tsmiButtonMenuExit});
            this.tsmiButtonMenuMainMenu.Name = "tsmiButtonMenuMainMenu";
            this.tsmiButtonMenuMainMenu.Size = new System.Drawing.Size(173, 22);
            this.tsmiButtonMenuMainMenu.Text = "Menu";
            // 
            // tsmiButtonMenuShow
            // 
            this.tsmiButtonMenuShow.Name = "tsmiButtonMenuShow";
            this.tsmiButtonMenuShow.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuShow.Text = "Show";
            this.tsmiButtonMenuShow.Click += new System.EventHandler(this.OnShow);
            // 
            // tsmiButtonMenuSeparator3
            // 
            this.tsmiButtonMenuSeparator3.Name = "tsmiButtonMenuSeparator3";
            this.tsmiButtonMenuSeparator3.Size = new System.Drawing.Size(147, 6);
            // 
            // tsmiButtonMenuGlobalSetting
            // 
            this.tsmiButtonMenuGlobalSetting.Name = "tsmiButtonMenuGlobalSetting";
            this.tsmiButtonMenuGlobalSetting.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuGlobalSetting.Text = "Global Setting";
            // 
            // tsmiButtonMenuButtonSetting
            // 
            this.tsmiButtonMenuButtonSetting.Name = "tsmiButtonMenuButtonSetting";
            this.tsmiButtonMenuButtonSetting.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuButtonSetting.Text = "Button Setting";
            this.tsmiButtonMenuButtonSetting.Click += new System.EventHandler(this.OnShowButtonSettingForm);
            // 
            // tsmiButtonMenuSkins
            // 
            this.tsmiButtonMenuSkins.Name = "tsmiButtonMenuSkins";
            this.tsmiButtonMenuSkins.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuSkins.Text = "Skin";
            // 
            // tsmiButtonMenuSeparator4
            // 
            this.tsmiButtonMenuSeparator4.Name = "tsmiButtonMenuSeparator4";
            this.tsmiButtonMenuSeparator4.Size = new System.Drawing.Size(147, 6);
            // 
            // tsmiButtonMenuHelp
            // 
            this.tsmiButtonMenuHelp.Name = "tsmiButtonMenuHelp";
            this.tsmiButtonMenuHelp.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuHelp.Text = "Help";
            // 
            // tsmiButtonMenuVersion
            // 
            this.tsmiButtonMenuVersion.Name = "tsmiButtonMenuVersion";
            this.tsmiButtonMenuVersion.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuVersion.Text = "Version";
            // 
            // tsmiButtonMenuSeparator5
            // 
            this.tsmiButtonMenuSeparator5.Name = "tsmiButtonMenuSeparator5";
            this.tsmiButtonMenuSeparator5.Size = new System.Drawing.Size(147, 6);
            // 
            // tsmiButtonMenuExit
            // 
            this.tsmiButtonMenuExit.Name = "tsmiButtonMenuExit";
            this.tsmiButtonMenuExit.Size = new System.Drawing.Size(150, 22);
            this.tsmiButtonMenuExit.Text = "Exit";
            // 
            // pnlContent
            // 
            this.pnlContent.BackColor = System.Drawing.SystemColors.GradientInactiveCaption;
            this.pnlContent.Controls.Add(this.flpContent);
            this.pnlContent.Controls.Add(this.flpTabs);
            this.pnlContent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlContent.Location = new System.Drawing.Point(0, 25);
            this.pnlContent.Name = "pnlContent";
            this.pnlContent.Size = new System.Drawing.Size(400, 375);
            this.pnlContent.TabIndex = 5;
            // 
            // flpContent
            // 
            this.flpContent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpContent.Location = new System.Drawing.Point(0, 25);
            this.flpContent.Margin = new System.Windows.Forms.Padding(4);
            this.flpContent.Name = "flpContent";
            this.flpContent.Size = new System.Drawing.Size(400, 350);
            this.flpContent.TabIndex = 1;
            // 
            // flpTabs
            // 
            this.flpTabs.BackColor = System.Drawing.SystemColors.GradientInactiveCaption;
            this.flpTabs.ContextMenuStrip = this.ctrNotifyMenuContext;
            this.flpTabs.Dock = System.Windows.Forms.DockStyle.Top;
            this.flpTabs.Location = new System.Drawing.Point(0, 0);
            this.flpTabs.Margin = new System.Windows.Forms.Padding(4);
            this.flpTabs.Name = "flpTabs";
            this.flpTabs.Size = new System.Drawing.Size(400, 25);
            this.flpTabs.TabIndex = 0;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoValidate = System.Windows.Forms.AutoValidate.Disable;
            this.CausesValidation = false;
            this.ClientSize = new System.Drawing.Size(400, 400);
            this.Controls.Add(this.pnlContent);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "MainForm";
            this.ResizeBegin += new System.EventHandler(this.MainForm_ResizeBegin);
            this.ResizeEnd += new System.EventHandler(this.MainForm_ResizeEnd);
            this.Paint += new System.Windows.Forms.PaintEventHandler(this.MainForm_Paint);
            this.Resize += new System.EventHandler(this.MainForm_Resize);
            this.Controls.SetChildIndex(this.pnlCaption, 0);
            this.Controls.SetChildIndex(this.pnlContent, 0);
            this.ctrNotifyMenuContext.ResumeLayout(false);
            this.ctrButtonMenuContext.ResumeLayout(false);
            this.pnlContent.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.NotifyIcon ctrNotifyIcon;
        private System.Windows.Forms.ContextMenuStrip ctrNotifyMenuContext;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuShow;
        private System.Windows.Forms.ToolStripSeparator tsNotifyMenuSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuGlobalSetting;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuButtonSetting;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuSkins;
        private System.Windows.Forms.ToolStripSeparator tsNotifyMenuSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuHelp;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuVersion;
        private System.Windows.Forms.ToolStripSeparator tsNotifyMenuSeparator3;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyMenuExit;
        private System.Windows.Forms.ContextMenuStrip ctrButtonMenuContext;
        private System.Windows.Forms.ToolStripMenuItem tsmiOpenFileLocation;
        private System.Windows.Forms.ToolStripMenuItem tsmiFileAttribute;
        private System.Windows.Forms.ToolStripSeparator tsButtonMenuSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiAddButton;
        private System.Windows.Forms.ToolStripMenuItem tsmiEditButton;
        private System.Windows.Forms.ToolStripMenuItem tsmiDeleteButton;
        private System.Windows.Forms.ToolStripMenuItem tsmiOrderButton;
        private System.Windows.Forms.ToolStripMenuItem tsmiAutoOrder;
        private System.Windows.Forms.ToolStripMenuItem tsmiOrderByName;
        private System.Windows.Forms.ToolStripMenuItem tsmiOrderByLocation;
        private System.Windows.Forms.ToolStripMenuItem tsmiOrderByUsage;
        private System.Windows.Forms.ToolStripMenuItem tsmiOrderByComment;
        private System.Windows.Forms.ToolStripSeparator tsButtonMenuSeparator2;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuPage;
        private System.Windows.Forms.ToolStripMenuItem tsmiNewPage;
        private System.Windows.Forms.ToolStripMenuItem tsmiEditPageName;
        private System.Windows.Forms.ToolStripMenuItem tsmiDeletePage;
        private System.Windows.Forms.ToolStripMenuItem tsmiMoveLeft;
        private System.Windows.Forms.ToolStripMenuItem tsmiMoveRight;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuMainMenu;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuShow;
        private System.Windows.Forms.ToolStripSeparator tsmiButtonMenuSeparator3;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuGlobalSetting;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuButtonSetting;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuSkins;
        private System.Windows.Forms.ToolStripSeparator tsmiButtonMenuSeparator4;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuHelp;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuVersion;
        private System.Windows.Forms.ToolStripSeparator tsmiButtonMenuSeparator5;
        private System.Windows.Forms.ToolStripMenuItem tsmiButtonMenuExit;
        private System.Windows.Forms.Panel pnlContent;
        private System.Windows.Forms.FlowLayoutPanel flpTabs;
        private System.Windows.Forms.FlowLayoutPanel flpContent;
        
    }
}
