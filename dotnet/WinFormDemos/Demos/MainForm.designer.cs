namespace Demo
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
            this.ctrContextMenu = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.configToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.closeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.ctrStatus = new System.Windows.Forms.StatusStrip();
            this.ctrTools = new System.Windows.Forms.ToolStrip();
            this.ctrMenu = new System.Windows.Forms.MenuStrip();
            this.tsmiForms = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNotifyInfo = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNonForm = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDialogs = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiColorDialog = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiModelessDialog = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiFormatNotificationDialog = new System.Windows.Forms.ToolStripMenuItem();
            this.layoutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDrawing = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDrawingBasic = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDrawingToTheScreen = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiPaintEvent = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiBrushDemos = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiTextureBrushs = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiHatchBrushs = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiLinearGradientBrushs = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiPathGradientBrushs = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiPenDemos = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiLineCaps = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDashes = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiPenAlignment = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiJoins = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCreatingPensFromBrushes = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiShapeDemos = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCurves = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiSmoothingModes = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiSavingAndRestoringGraphicsSettings = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiPathDemos = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiFillModes = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiImageDemos = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiLoadAndDrawingImges = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiScalingVsCliping = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiRotatingAndFlipping = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiRecoloring = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiTransparency = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiAnimation = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDrawingToImages = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiScreenCopying = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiIcons = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCursors = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiDrawingText = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiFont = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiString = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiRender = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiAdvance = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiPrint = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiComponents = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiControls = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiBinding = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiThreading = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCustom = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiWindows = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCloseAllWindows = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiNewWindow = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiTileHorizontally = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiTileVertically = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiArrangeIcon = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiCascade = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiHelp = new System.Windows.Forms.ToolStripMenuItem();
            this.ctrContextMenu.SuspendLayout();
            this.ctrMenu.SuspendLayout();
            this.SuspendLayout();
            // 
            // ctrNotifyIcon
            // 
            this.ctrNotifyIcon.BalloonTipIcon = System.Windows.Forms.ToolTipIcon.Info;
            this.ctrNotifyIcon.BalloonTipText = "Hello World!";
            this.ctrNotifyIcon.BalloonTipTitle = "WinForm Demo";
            this.ctrNotifyIcon.ContextMenuStrip = this.ctrContextMenu;
            this.ctrNotifyIcon.Icon = ((System.Drawing.Icon)(resources.GetObject("ctrNotifyIcon.Icon")));
            this.ctrNotifyIcon.Text = "Demo";
            this.ctrNotifyIcon.Visible = true;
            this.ctrNotifyIcon.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.ctrlNotifyIcon_MouseDoubleClick);
            // 
            // ctrContextMenu
            // 
            this.ctrContextMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.configToolStripMenuItem,
            this.closeToolStripMenuItem});
            this.ctrContextMenu.Name = "contextMenuStrip1";
            this.ctrContextMenu.Size = new System.Drawing.Size(111, 48);
            // 
            // configToolStripMenuItem
            // 
            this.configToolStripMenuItem.Name = "configToolStripMenuItem";
            this.configToolStripMenuItem.Size = new System.Drawing.Size(110, 22);
            this.configToolStripMenuItem.Text = "Config";
            // 
            // closeToolStripMenuItem
            // 
            this.closeToolStripMenuItem.Name = "closeToolStripMenuItem";
            this.closeToolStripMenuItem.Size = new System.Drawing.Size(110, 22);
            this.closeToolStripMenuItem.Text = "Close";
            // 
            // ctrStatus
            // 
            this.ctrStatus.Location = new System.Drawing.Point(0, 540);
            this.ctrStatus.Name = "ctrStatus";
            this.ctrStatus.Size = new System.Drawing.Size(784, 22);
            this.ctrStatus.TabIndex = 4;
            this.ctrStatus.Text = "statusStrip1";
            // 
            // ctrTools
            // 
            this.ctrTools.Location = new System.Drawing.Point(0, 24);
            this.ctrTools.Name = "ctrTools";
            this.ctrTools.Size = new System.Drawing.Size(784, 25);
            this.ctrTools.TabIndex = 4;
            this.ctrTools.Text = "toolStrip1";
            // 
            // ctrMenu
            // 
            this.ctrMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiForms,
            this.tsmiDialogs,
            this.layoutToolStripMenuItem,
            this.tsmiDrawing,
            this.tsmiPrint,
            this.tsmiComponents,
            this.tsmiControls,
            this.tsmiBinding,
            this.tsmiThreading,
            this.tsmiCustom,
            this.tsmiWindows,
            this.tsmiHelp});
            this.ctrMenu.Location = new System.Drawing.Point(0, 0);
            this.ctrMenu.MdiWindowListItem = this.tsmiWindows;
            this.ctrMenu.Name = "ctrMenu";
            this.ctrMenu.Size = new System.Drawing.Size(784, 24);
            this.ctrMenu.TabIndex = 5;
            this.ctrMenu.Text = "menuStrip1";
            // 
            // tsmiForms
            // 
            this.tsmiForms.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiNotifyInfo,
            this.tsmiNonForm});
            this.tsmiForms.Name = "tsmiForms";
            this.tsmiForms.Size = new System.Drawing.Size(52, 20);
            this.tsmiForms.Text = "Forms";
            // 
            // tsmiNotifyInfo
            // 
            this.tsmiNotifyInfo.Name = "tsmiNotifyInfo";
            this.tsmiNotifyInfo.Size = new System.Drawing.Size(134, 22);
            this.tsmiNotifyInfo.Text = "通知区域";
            this.tsmiNotifyInfo.Click += new System.EventHandler(this.tsmiNotification_Click);
            // 
            // tsmiNonForm
            // 
            this.tsmiNonForm.Name = "tsmiNonForm";
            this.tsmiNonForm.Size = new System.Drawing.Size(134, 22);
            this.tsmiNonForm.Text = "不规则窗体";
            this.tsmiNonForm.Click += new System.EventHandler(this.tsmiNonForm_Click);
            // 
            // tsmiDialogs
            // 
            this.tsmiDialogs.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiColorDialog,
            this.tsmiModelessDialog,
            this.tsmiFormatNotificationDialog});
            this.tsmiDialogs.Name = "tsmiDialogs";
            this.tsmiDialogs.Size = new System.Drawing.Size(58, 20);
            this.tsmiDialogs.Text = "Dialogs";
            // 
            // tsmiColorDialog
            // 
            this.tsmiColorDialog.Name = "tsmiColorDialog";
            this.tsmiColorDialog.Size = new System.Drawing.Size(209, 22);
            this.tsmiColorDialog.Text = "ColorDialog";
            this.tsmiColorDialog.Click += new System.EventHandler(this.tsmiColorDialog_Click);
            // 
            // tsmiModelessDialog
            // 
            this.tsmiModelessDialog.Name = "tsmiModelessDialog";
            this.tsmiModelessDialog.Size = new System.Drawing.Size(209, 22);
            this.tsmiModelessDialog.Text = "ModelessDialog";
            this.tsmiModelessDialog.Click += new System.EventHandler(this.tsmiModelessDialog_Click);
            // 
            // tsmiFormatNotificationDialog
            // 
            this.tsmiFormatNotificationDialog.Name = "tsmiFormatNotificationDialog";
            this.tsmiFormatNotificationDialog.Size = new System.Drawing.Size(209, 22);
            this.tsmiFormatNotificationDialog.Text = "FormatNotificationDialog";
            this.tsmiFormatNotificationDialog.Click += new System.EventHandler(this.tsmiFormatNotificationDialog_Click);
            // 
            // layoutToolStripMenuItem
            // 
            this.layoutToolStripMenuItem.Name = "layoutToolStripMenuItem";
            this.layoutToolStripMenuItem.Size = new System.Drawing.Size(55, 20);
            this.layoutToolStripMenuItem.Text = "Layout";
            // 
            // tsmiDrawing
            // 
            this.tsmiDrawing.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiDrawingBasic,
            this.tsmiDrawingText,
            this.tsmiAdvance});
            this.tsmiDrawing.Name = "tsmiDrawing";
            this.tsmiDrawing.Size = new System.Drawing.Size(63, 20);
            this.tsmiDrawing.Text = "Drawing";
            // 
            // tsmiDrawingBasic
            // 
            this.tsmiDrawingBasic.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiDrawingToTheScreen,
            this.tsmiPaintEvent,
            this.tsmiBrushDemos,
            this.tsmiPenDemos,
            this.tsmiShapeDemos,
            this.tsmiPathDemos,
            this.tsmiImageDemos});
            this.tsmiDrawingBasic.Name = "tsmiDrawingBasic";
            this.tsmiDrawingBasic.Size = new System.Drawing.Size(152, 22);
            this.tsmiDrawingBasic.Text = "Basic";
            // 
            // tsmiDrawingToTheScreen
            // 
            this.tsmiDrawingToTheScreen.Name = "tsmiDrawingToTheScreen";
            this.tsmiDrawingToTheScreen.Size = new System.Drawing.Size(190, 22);
            this.tsmiDrawingToTheScreen.Text = "Drawing to the Screen";
            this.tsmiDrawingToTheScreen.Click += new System.EventHandler(this.tsmiDrawingToTheScreen_Click);
            // 
            // tsmiPaintEvent
            // 
            this.tsmiPaintEvent.Name = "tsmiPaintEvent";
            this.tsmiPaintEvent.Size = new System.Drawing.Size(190, 22);
            this.tsmiPaintEvent.Text = "PaintEvent";
            this.tsmiPaintEvent.Click += new System.EventHandler(this.tsmiPaintEvent_Click);
            // 
            // tsmiBrushDemos
            // 
            this.tsmiBrushDemos.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiTextureBrushs,
            this.tsmiHatchBrushs,
            this.tsmiLinearGradientBrushs,
            this.tsmiPathGradientBrushs});
            this.tsmiBrushDemos.Name = "tsmiBrushDemos";
            this.tsmiBrushDemos.Size = new System.Drawing.Size(190, 22);
            this.tsmiBrushDemos.Text = "Brush Demos";
            this.tsmiBrushDemos.Click += new System.EventHandler(this.tsmiBrushDemos_Click);
            // 
            // tsmiTextureBrushs
            // 
            this.tsmiTextureBrushs.Name = "tsmiTextureBrushs";
            this.tsmiTextureBrushs.Size = new System.Drawing.Size(186, 22);
            this.tsmiTextureBrushs.Text = "TextureBrushs";
            this.tsmiTextureBrushs.Click += new System.EventHandler(this.tsmiTextureBrushs_Click);
            // 
            // tsmiHatchBrushs
            // 
            this.tsmiHatchBrushs.Name = "tsmiHatchBrushs";
            this.tsmiHatchBrushs.Size = new System.Drawing.Size(186, 22);
            this.tsmiHatchBrushs.Text = "HatchBrushs";
            this.tsmiHatchBrushs.Click += new System.EventHandler(this.tsmiHatchBrushs_Click);
            // 
            // tsmiLinearGradientBrushs
            // 
            this.tsmiLinearGradientBrushs.Name = "tsmiLinearGradientBrushs";
            this.tsmiLinearGradientBrushs.Size = new System.Drawing.Size(186, 22);
            this.tsmiLinearGradientBrushs.Text = "LinearGradientBrushs";
            this.tsmiLinearGradientBrushs.Click += new System.EventHandler(this.tsmiLinearGradientBrushs_Click);
            // 
            // tsmiPathGradientBrushs
            // 
            this.tsmiPathGradientBrushs.Name = "tsmiPathGradientBrushs";
            this.tsmiPathGradientBrushs.Size = new System.Drawing.Size(186, 22);
            this.tsmiPathGradientBrushs.Text = "PathGradientBrushs";
            this.tsmiPathGradientBrushs.Click += new System.EventHandler(this.tsmiPathGradientBrushs_Click);
            // 
            // tsmiPenDemos
            // 
            this.tsmiPenDemos.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiLineCaps,
            this.tsmiDashes,
            this.tsmiPenAlignment,
            this.tsmiJoins,
            this.tsmiCreatingPensFromBrushes});
            this.tsmiPenDemos.Name = "tsmiPenDemos";
            this.tsmiPenDemos.Size = new System.Drawing.Size(190, 22);
            this.tsmiPenDemos.Text = "Pen Demos";
            // 
            // tsmiLineCaps
            // 
            this.tsmiLineCaps.Name = "tsmiLineCaps";
            this.tsmiLineCaps.Size = new System.Drawing.Size(222, 22);
            this.tsmiLineCaps.Text = "Line Caps";
            this.tsmiLineCaps.Click += new System.EventHandler(this.tsmiLineCaps_Click);
            // 
            // tsmiDashes
            // 
            this.tsmiDashes.Name = "tsmiDashes";
            this.tsmiDashes.Size = new System.Drawing.Size(222, 22);
            this.tsmiDashes.Text = "Dashes";
            this.tsmiDashes.Click += new System.EventHandler(this.tsmiDashes_Click);
            // 
            // tsmiPenAlignment
            // 
            this.tsmiPenAlignment.Name = "tsmiPenAlignment";
            this.tsmiPenAlignment.Size = new System.Drawing.Size(222, 22);
            this.tsmiPenAlignment.Text = "Pen Alignment";
            this.tsmiPenAlignment.Click += new System.EventHandler(this.tsmiPenAlignment_Click);
            // 
            // tsmiJoins
            // 
            this.tsmiJoins.Name = "tsmiJoins";
            this.tsmiJoins.Size = new System.Drawing.Size(222, 22);
            this.tsmiJoins.Text = "Joins";
            this.tsmiJoins.Click += new System.EventHandler(this.tsmiJoins_Click);
            // 
            // tsmiCreatingPensFromBrushes
            // 
            this.tsmiCreatingPensFromBrushes.Name = "tsmiCreatingPensFromBrushes";
            this.tsmiCreatingPensFromBrushes.Size = new System.Drawing.Size(222, 22);
            this.tsmiCreatingPensFromBrushes.Text = "Creating Pens From Brushes";
            this.tsmiCreatingPensFromBrushes.Click += new System.EventHandler(this.tsmiCreatingPensFromBrushes_Click);
            // 
            // tsmiShapeDemos
            // 
            this.tsmiShapeDemos.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiCurves,
            this.tsmiSmoothingModes,
            this.tsmiSavingAndRestoringGraphicsSettings});
            this.tsmiShapeDemos.Name = "tsmiShapeDemos";
            this.tsmiShapeDemos.Size = new System.Drawing.Size(190, 22);
            this.tsmiShapeDemos.Text = "Shape Demos";
            // 
            // tsmiCurves
            // 
            this.tsmiCurves.Name = "tsmiCurves";
            this.tsmiCurves.Size = new System.Drawing.Size(279, 22);
            this.tsmiCurves.Text = "Curves";
            this.tsmiCurves.Click += new System.EventHandler(this.tsmiCurves_Click);
            // 
            // tsmiSmoothingModes
            // 
            this.tsmiSmoothingModes.Name = "tsmiSmoothingModes";
            this.tsmiSmoothingModes.Size = new System.Drawing.Size(279, 22);
            this.tsmiSmoothingModes.Text = "Smoothing Modes";
            this.tsmiSmoothingModes.Click += new System.EventHandler(this.tsmiSmoothingModes_Click);
            // 
            // tsmiSavingAndRestoringGraphicsSettings
            // 
            this.tsmiSavingAndRestoringGraphicsSettings.Name = "tsmiSavingAndRestoringGraphicsSettings";
            this.tsmiSavingAndRestoringGraphicsSettings.Size = new System.Drawing.Size(279, 22);
            this.tsmiSavingAndRestoringGraphicsSettings.Text = "Saving and Restoring Graphics Settings";
            this.tsmiSavingAndRestoringGraphicsSettings.Click += new System.EventHandler(this.tsmiSavingAndRestoringGraphicsSettings_Click);
            // 
            // tsmiPathDemos
            // 
            this.tsmiPathDemos.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiFillModes});
            this.tsmiPathDemos.Name = "tsmiPathDemos";
            this.tsmiPathDemos.Size = new System.Drawing.Size(190, 22);
            this.tsmiPathDemos.Text = "Path Demos";
            this.tsmiPathDemos.Click += new System.EventHandler(this.tsmiPathDemos_Click);
            // 
            // tsmiFillModes
            // 
            this.tsmiFillModes.Name = "tsmiFillModes";
            this.tsmiFillModes.Size = new System.Drawing.Size(152, 22);
            this.tsmiFillModes.Text = "Fill Modes";
            this.tsmiFillModes.Click += new System.EventHandler(this.tsmiFillModes_Click);
            // 
            // tsmiImageDemos
            // 
            this.tsmiImageDemos.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiLoadAndDrawingImges,
            this.tsmiScalingVsCliping,
            this.tsmiRotatingAndFlipping,
            this.tsmiRecoloring,
            this.tsmiTransparency,
            this.tsmiAnimation,
            this.tsmiDrawingToImages,
            this.tsmiScreenCopying,
            this.tsmiIcons,
            this.tsmiCursors});
            this.tsmiImageDemos.Name = "tsmiImageDemos";
            this.tsmiImageDemos.Size = new System.Drawing.Size(190, 22);
            this.tsmiImageDemos.Text = "Image Demos";
            // 
            // tsmiLoadAndDrawingImges
            // 
            this.tsmiLoadAndDrawingImges.Name = "tsmiLoadAndDrawingImges";
            this.tsmiLoadAndDrawingImges.Size = new System.Drawing.Size(205, 22);
            this.tsmiLoadAndDrawingImges.Text = "Load and Drawing Imges";
            this.tsmiLoadAndDrawingImges.Click += new System.EventHandler(this.tsmiLoadAndDrawingImges_Click);
            // 
            // tsmiScalingVsCliping
            // 
            this.tsmiScalingVsCliping.Name = "tsmiScalingVsCliping";
            this.tsmiScalingVsCliping.Size = new System.Drawing.Size(205, 22);
            this.tsmiScalingVsCliping.Text = "Scaling and Skewing";
            this.tsmiScalingVsCliping.Click += new System.EventHandler(this.tsmiScalingAndSkewing_Click);
            // 
            // tsmiRotatingAndFlipping
            // 
            this.tsmiRotatingAndFlipping.Name = "tsmiRotatingAndFlipping";
            this.tsmiRotatingAndFlipping.Size = new System.Drawing.Size(205, 22);
            this.tsmiRotatingAndFlipping.Text = "Rotating and Flipping";
            this.tsmiRotatingAndFlipping.Click += new System.EventHandler(this.tsmiRotatingAndFlipping_Click);
            // 
            // tsmiRecoloring
            // 
            this.tsmiRecoloring.Name = "tsmiRecoloring";
            this.tsmiRecoloring.Size = new System.Drawing.Size(205, 22);
            this.tsmiRecoloring.Text = "Recoloring";
            this.tsmiRecoloring.Click += new System.EventHandler(this.tsmiRecoloring_Click);
            // 
            // tsmiTransparency
            // 
            this.tsmiTransparency.Name = "tsmiTransparency";
            this.tsmiTransparency.Size = new System.Drawing.Size(205, 22);
            this.tsmiTransparency.Text = "Transparency";
            this.tsmiTransparency.Click += new System.EventHandler(this.tsmiTransparency_Click);
            // 
            // tsmiAnimation
            // 
            this.tsmiAnimation.Name = "tsmiAnimation";
            this.tsmiAnimation.Size = new System.Drawing.Size(205, 22);
            this.tsmiAnimation.Text = "Animation";
            this.tsmiAnimation.Click += new System.EventHandler(this.tsmiAnimation_Click);
            // 
            // tsmiDrawingToImages
            // 
            this.tsmiDrawingToImages.Name = "tsmiDrawingToImages";
            this.tsmiDrawingToImages.Size = new System.Drawing.Size(205, 22);
            this.tsmiDrawingToImages.Text = "Drawing to Images";
            this.tsmiDrawingToImages.Click += new System.EventHandler(this.tsmiDrawingToImages_Click);
            // 
            // tsmiScreenCopying
            // 
            this.tsmiScreenCopying.Name = "tsmiScreenCopying";
            this.tsmiScreenCopying.Size = new System.Drawing.Size(205, 22);
            this.tsmiScreenCopying.Text = "Screen Copying";
            this.tsmiScreenCopying.Click += new System.EventHandler(this.tsmiScreenCopying_Click);
            // 
            // tsmiIcons
            // 
            this.tsmiIcons.Name = "tsmiIcons";
            this.tsmiIcons.Size = new System.Drawing.Size(205, 22);
            this.tsmiIcons.Text = "Icons";
            this.tsmiIcons.Click += new System.EventHandler(this.tsmiIcons_Click);
            // 
            // tsmiCursors
            // 
            this.tsmiCursors.Name = "tsmiCursors";
            this.tsmiCursors.Size = new System.Drawing.Size(205, 22);
            this.tsmiCursors.Text = "Cursors";
            this.tsmiCursors.Click += new System.EventHandler(this.tsmiCursors_Click);
            // 
            // tsmiDrawingText
            // 
            this.tsmiDrawingText.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiFont,
            this.tsmiString,
            this.tsmiRender});
            this.tsmiDrawingText.Name = "tsmiDrawingText";
            this.tsmiDrawingText.Size = new System.Drawing.Size(152, 22);
            this.tsmiDrawingText.Text = "Text";
            // 
            // tsmiFont
            // 
            this.tsmiFont.Name = "tsmiFont";
            this.tsmiFont.Size = new System.Drawing.Size(111, 22);
            this.tsmiFont.Text = "Font";
            // 
            // tsmiString
            // 
            this.tsmiString.Name = "tsmiString";
            this.tsmiString.Size = new System.Drawing.Size(111, 22);
            this.tsmiString.Text = "String";
            // 
            // tsmiRender
            // 
            this.tsmiRender.Name = "tsmiRender";
            this.tsmiRender.Size = new System.Drawing.Size(111, 22);
            this.tsmiRender.Text = "Render";
            // 
            // tsmiAdvance
            // 
            this.tsmiAdvance.Name = "tsmiAdvance";
            this.tsmiAdvance.Size = new System.Drawing.Size(152, 22);
            this.tsmiAdvance.Text = "Advance";
            // 
            // tsmiPrint
            // 
            this.tsmiPrint.Name = "tsmiPrint";
            this.tsmiPrint.Size = new System.Drawing.Size(44, 20);
            this.tsmiPrint.Text = "Print";
            // 
            // tsmiComponents
            // 
            this.tsmiComponents.Name = "tsmiComponents";
            this.tsmiComponents.Size = new System.Drawing.Size(88, 20);
            this.tsmiComponents.Text = "Components";
            // 
            // tsmiControls
            // 
            this.tsmiControls.Name = "tsmiControls";
            this.tsmiControls.Size = new System.Drawing.Size(64, 20);
            this.tsmiControls.Text = "Controls";
            // 
            // tsmiBinding
            // 
            this.tsmiBinding.Name = "tsmiBinding";
            this.tsmiBinding.Size = new System.Drawing.Size(60, 20);
            this.tsmiBinding.Text = "Binding";
            // 
            // tsmiThreading
            // 
            this.tsmiThreading.Name = "tsmiThreading";
            this.tsmiThreading.Size = new System.Drawing.Size(56, 20);
            this.tsmiThreading.Text = "Thread";
            // 
            // tsmiCustom
            // 
            this.tsmiCustom.Name = "tsmiCustom";
            this.tsmiCustom.Size = new System.Drawing.Size(61, 20);
            this.tsmiCustom.Text = "Custom";
            // 
            // tsmiWindows
            // 
            this.tsmiWindows.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiCloseAllWindows,
            this.tsmiNewWindow,
            this.tsmiTileHorizontally,
            this.tsmiTileVertically,
            this.tsmiArrangeIcon,
            this.tsmiCascade,
            this.toolStripSeparator1});
            this.tsmiWindows.Name = "tsmiWindows";
            this.tsmiWindows.Size = new System.Drawing.Size(68, 20);
            this.tsmiWindows.Text = "Windows";
            // 
            // tsmiCloseAllWindows
            // 
            this.tsmiCloseAllWindows.Name = "tsmiCloseAllWindows";
            this.tsmiCloseAllWindows.Size = new System.Drawing.Size(160, 22);
            this.tsmiCloseAllWindows.Text = "Close All";
            this.tsmiCloseAllWindows.Click += new System.EventHandler(this.tsmiCloseAllWindows_Click);
            // 
            // tsmiNewWindow
            // 
            this.tsmiNewWindow.Name = "tsmiNewWindow";
            this.tsmiNewWindow.Size = new System.Drawing.Size(160, 22);
            this.tsmiNewWindow.Text = "New Window";
            this.tsmiNewWindow.Click += new System.EventHandler(this.tsmiNewWindow_Click);
            // 
            // tsmiTileHorizontally
            // 
            this.tsmiTileHorizontally.Name = "tsmiTileHorizontally";
            this.tsmiTileHorizontally.Size = new System.Drawing.Size(160, 22);
            this.tsmiTileHorizontally.Text = "Tile Horizontally";
            this.tsmiTileHorizontally.Click += new System.EventHandler(this.tsmiTileHorizontally_Click);
            // 
            // tsmiTileVertically
            // 
            this.tsmiTileVertically.Name = "tsmiTileVertically";
            this.tsmiTileVertically.Size = new System.Drawing.Size(160, 22);
            this.tsmiTileVertically.Text = "Tile Vertically";
            this.tsmiTileVertically.Click += new System.EventHandler(this.tsmiTileVertically_Click);
            // 
            // tsmiArrangeIcon
            // 
            this.tsmiArrangeIcon.Name = "tsmiArrangeIcon";
            this.tsmiArrangeIcon.Size = new System.Drawing.Size(160, 22);
            this.tsmiArrangeIcon.Text = "Arrange Icons";
            this.tsmiArrangeIcon.Click += new System.EventHandler(this.tsmiArrangeIcon_Click);
            // 
            // tsmiCascade
            // 
            this.tsmiCascade.Name = "tsmiCascade";
            this.tsmiCascade.Size = new System.Drawing.Size(160, 22);
            this.tsmiCascade.Text = "Cascade";
            this.tsmiCascade.Click += new System.EventHandler(this.tsmiCascade_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(157, 6);
            // 
            // tsmiHelp
            // 
            this.tsmiHelp.Name = "tsmiHelp";
            this.tsmiHelp.Size = new System.Drawing.Size(44, 20);
            this.tsmiHelp.Text = "Help";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 562);
            this.Controls.Add(this.ctrTools);
            this.Controls.Add(this.ctrMenu);
            this.Controls.Add(this.ctrStatus);
            this.IsMdiContainer = true;
            this.MainMenuStrip = this.ctrMenu;
            this.Name = "MainForm";
            this.Opacity = 0.95D;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "WinForm Demo";
            this.Resize += new System.EventHandler(this.MainForm_Resize);
            this.ctrContextMenu.ResumeLayout(false);
            this.ctrMenu.ResumeLayout(false);
            this.ctrMenu.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.NotifyIcon ctrNotifyIcon;
        private System.Windows.Forms.StatusStrip ctrStatus;
        private System.Windows.Forms.ToolStrip ctrTools;
        private System.Windows.Forms.ContextMenuStrip ctrContextMenu;
        private System.Windows.Forms.MenuStrip ctrMenu;
        private System.Windows.Forms.ToolStripMenuItem tsmiForms;
        private System.Windows.Forms.ToolStripMenuItem tsmiNotifyInfo;
        private System.Windows.Forms.ToolStripMenuItem tsmiNonForm;
        private System.Windows.Forms.ToolStripMenuItem tsmiDialogs;
        private System.Windows.Forms.ToolStripMenuItem tsmiWindows;
        private System.Windows.Forms.ToolStripMenuItem tsmiNewWindow;
        private System.Windows.Forms.ToolStripMenuItem tsmiArrangeIcon;
        private System.Windows.Forms.ToolStripMenuItem tsmiCascade;
        private System.Windows.Forms.ToolStripMenuItem tsmiTileHorizontally;
        private System.Windows.Forms.ToolStripMenuItem tsmiTileVertically;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem tsmiColorDialog;
        private System.Windows.Forms.ToolStripMenuItem tsmiModelessDialog;
        private System.Windows.Forms.ToolStripMenuItem tsmiFormatNotificationDialog;
        private System.Windows.Forms.ToolStripMenuItem layoutToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem configToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem closeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem tsmiCloseAllWindows;
        private System.Windows.Forms.ToolStripMenuItem tsmiDrawing;
        private System.Windows.Forms.ToolStripMenuItem tsmiDrawingBasic;
        private System.Windows.Forms.ToolStripMenuItem tsmiDrawingText;
        private System.Windows.Forms.ToolStripMenuItem tsmiAdvance;
        private System.Windows.Forms.ToolStripMenuItem tsmiHelp;
        private System.Windows.Forms.ToolStripMenuItem tsmiPrint;
        private System.Windows.Forms.ToolStripMenuItem tsmiComponents;
        private System.Windows.Forms.ToolStripMenuItem tsmiControls;
        private System.Windows.Forms.ToolStripMenuItem tsmiBinding;
        private System.Windows.Forms.ToolStripMenuItem tsmiThreading;
        private System.Windows.Forms.ToolStripMenuItem tsmiCustom;
        private System.Windows.Forms.ToolStripMenuItem tsmiDrawingToTheScreen;
        private System.Windows.Forms.ToolStripMenuItem tsmiPaintEvent;
        private System.Windows.Forms.ToolStripMenuItem tsmiBrushDemos;
        private System.Windows.Forms.ToolStripMenuItem tsmiTextureBrushs;
        private System.Windows.Forms.ToolStripMenuItem tsmiHatchBrushs;
        private System.Windows.Forms.ToolStripMenuItem tsmiLinearGradientBrushs;
        private System.Windows.Forms.ToolStripMenuItem tsmiPathGradientBrushs;
        private System.Windows.Forms.ToolStripMenuItem tsmiPenDemos;
        private System.Windows.Forms.ToolStripMenuItem tsmiShapeDemos;
        private System.Windows.Forms.ToolStripMenuItem tsmiPathDemos;
        private System.Windows.Forms.ToolStripMenuItem tsmiImageDemos;
        private System.Windows.Forms.ToolStripMenuItem tsmiFont;
        private System.Windows.Forms.ToolStripMenuItem tsmiString;
        private System.Windows.Forms.ToolStripMenuItem tsmiRender;
        private System.Windows.Forms.ToolStripMenuItem tsmiLineCaps;
        private System.Windows.Forms.ToolStripMenuItem tsmiDashes;
        private System.Windows.Forms.ToolStripMenuItem tsmiPenAlignment;
        private System.Windows.Forms.ToolStripMenuItem tsmiJoins;
        private System.Windows.Forms.ToolStripMenuItem tsmiCreatingPensFromBrushes;
        private System.Windows.Forms.ToolStripMenuItem tsmiCurves;
        private System.Windows.Forms.ToolStripMenuItem tsmiSmoothingModes;
        private System.Windows.Forms.ToolStripMenuItem tsmiSavingAndRestoringGraphicsSettings;
        private System.Windows.Forms.ToolStripMenuItem tsmiLoadAndDrawingImges;
        private System.Windows.Forms.ToolStripMenuItem tsmiScalingVsCliping;
        private System.Windows.Forms.ToolStripMenuItem tsmiRotatingAndFlipping;
        private System.Windows.Forms.ToolStripMenuItem tsmiRecoloring;
        private System.Windows.Forms.ToolStripMenuItem tsmiTransparency;
        private System.Windows.Forms.ToolStripMenuItem tsmiAnimation;
        private System.Windows.Forms.ToolStripMenuItem tsmiDrawingToImages;
        private System.Windows.Forms.ToolStripMenuItem tsmiScreenCopying;
        private System.Windows.Forms.ToolStripMenuItem tsmiIcons;
        private System.Windows.Forms.ToolStripMenuItem tsmiCursors;
        private System.Windows.Forms.ToolStripMenuItem tsmiFillModes;
        
    }
}

