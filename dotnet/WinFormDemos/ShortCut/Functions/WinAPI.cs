using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;
using System.Drawing;
using System.Windows.Forms;

namespace ShortCut
{
    public class API
    {

        public class FileIcon
        {

            [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Auto)]
            public struct SHFILEINFO
            {
                public IntPtr hIcon;
                public int iIcon;
                public uint dwAttributes;
                [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 260)]
                public string szDisplayName;
                [MarshalAs(UnmanagedType.ByValTStr, SizeConst = 80)]
                public string szTypeName;
            };

            public enum FolderType : uint
            {
                Closed,
                Open = SHFILEATTR.SHGFI_OPENICON
            }

            public enum IconSize : uint
            {
                Large = SHFILEATTR.SHGFI_LARGEICON,
                Small = SHFILEATTR.SHGFI_SMALLICON
            }

            enum SHFILEATTR : uint 
            {
                SHGFI_ICON = 0x000000100,
                SHGFI_DISPLAYNAME = 0x000000200,
                SHGFI_TYPENAME = 0x000000400,
                SHGFI_ATTRIBUTES = 0x000000800,
                SHGFI_ICONLOCATION = 0x000001000,
                SHGFI_EXETYPE = 0x000002000,
                SHGFI_SYSICONINDEX = 0x000004000,
                SHGFI_LINKOVERLAY = 0x000008000,
                SHGFI_SELECTED = 0x000010000,
                SHGFI_ATTR_SPECIFIED = 0x000020000,
                SHGFI_LARGEICON = 0x000000000,
                SHGFI_SMALLICON = 0x000000001,
                SHGFI_OPENICON = 0x000000002,
                SHGFI_SHELLICONSIZE = 0x000000004,
                SHGFI_PIDL = 0x000000008,
                SHGFI_USEFILEATTRIBUTES = 0x000000010,
                SHGFI_ADDOVERLAYS = 0x000000020,
                SHGFI_OVERLAYINDEX = 0x000000040
            }

            enum FILEATTR : uint 
            {
                FILE_ATTRIBUTE_READONLY             =   0x00000001,
                FILE_ATTRIBUTE_HIDDEN               =   0x00000002,  
                FILE_ATTRIBUTE_SYSTEM               =   0x00000004,  
                FILE_ATTRIBUTE_DIRECTORY            =   0x00000010,  
                FILE_ATTRIBUTE_ARCHIVE              =   0x00000020,  
                FILE_ATTRIBUTE_ENCRYPTED            =   0x00000040,  
                FILE_ATTRIBUTE_NORMAL               =   0x00000080,  
                FILE_ATTRIBUTE_TEMPORARY            =   0x00000100,  
                FILE_ATTRIBUTE_SPARSE_FILE          =   0x00000200,  
                FILE_ATTRIBUTE_REPARSE_POINT        =   0x00000400,  
                FILE_ATTRIBUTE_COMPRESSED           =   0x00000800,  
                FILE_ATTRIBUTE_OFFLINE              =   0x00001000,  
                FILE_ATTRIBUTE_NOT_CONTENT_INDEXED  =   0x00002000, 
            }

            [DllImport("shell32.dll", CharSet = CharSet.Auto)]
            static extern IntPtr SHGetFileInfo(string pszPath, uint dwFileAttributes, out SHFILEINFO psfi, uint cbFileInfo, uint uFlags);

            [DllImport("user32.dll", SetLastError = true)]
            [return: MarshalAs(UnmanagedType.Bool)]
            static extern bool DestroyIcon(IntPtr hIcon);

            public static Icon GetFileIcon(string path, IconSize size) 
            {
                // Need to add size check, although errors generated at present!    
                uint flags = (uint)SHFILEATTR.SHGFI_ICON | (uint)SHFILEATTR.SHGFI_USEFILEATTRIBUTES;

                flags += (uint)SHFILEATTR.SHGFI_OPENICON;
                flags += (uint)size;

                // Get the folder icon    
                SHFILEINFO shfi = new SHFILEINFO();

                var res = SHGetFileInfo(path,
                    (uint)FILEATTR.FILE_ATTRIBUTE_ARCHIVE,
                    out shfi,
                    (uint)Marshal.SizeOf(shfi),
                    flags);

                if (res == IntPtr.Zero)
                    throw Marshal.GetExceptionForHR(Marshal.GetHRForLastWin32Error());

                // Load the icon from an HICON handle  
                Icon.FromHandle(shfi.hIcon);

                // Now clone the icon, so that it can be successfully stored in an ImageList
                var icon = (Icon)Icon.FromHandle(shfi.hIcon).Clone();

                DestroyIcon(shfi.hIcon);        // Cleanup    

                return icon;
            }

            public static Icon GetFolderIcon(string path, IconSize size)
            {
                // Need to add size check, although errors generated at present!    
                uint flags = (uint)SHFILEATTR.SHGFI_ICON | (uint)SHFILEATTR.SHGFI_USEFILEATTRIBUTES;

                flags += (uint)SHFILEATTR.SHGFI_OPENICON;
                flags += (uint)size;

                // Get the folder icon    
                SHFILEINFO shfi = new SHFILEINFO();

                var res = SHGetFileInfo(path,
                    (int)FILEATTR.FILE_ATTRIBUTE_DIRECTORY,
                    out shfi,
                    (uint)Marshal.SizeOf(shfi),
                    flags);

                if (res == IntPtr.Zero)
                    throw Marshal.GetExceptionForHR(Marshal.GetHRForLastWin32Error());

                // Load the icon from an HICON handle  
                Icon.FromHandle(shfi.hIcon);

                // Now clone the icon, so that it can be successfully stored in an ImageList
                var icon = (Icon)Icon.FromHandle(shfi.hIcon).Clone();

                DestroyIcon(shfi.hIcon);        // Cleanup    

                return icon;
            }
            
        }

        #region Dock Application To Desktop

        [DllImport("user32.dll", EntryPoint = "SetParent")]  
        public static extern int SetParent(int hWndChild, int hWndNewParent);  
  
        [DllImport("user32.dll", EntryPoint = "FindWindow")]  
        public static extern int FindWindow(string lpClassName, string lpWindowName);  


        #endregion


        public class AppBar
        {
            [StructLayout(LayoutKind.Sequential)]
            private struct RECT
            {
                public int left;
                public int top;
                public int right;
                public int bottom;
            }

            [StructLayout(LayoutKind.Sequential)]
            private struct APPBARDATA
            {
                public int cbSize;
                public IntPtr hWnd;
                public int uCallbackMessage;
                public int uEdge;
                public RECT rc;
                public IntPtr lParam;
            }

            private enum ABMsg : int
            {
                ABM_NEW = 0,
                ABM_REMOVE = 1,
                ABM_QUERYPOS = 2,
                ABM_SETPOS = 3,
                ABM_GETSTATE = 4,
                ABM_GETTASKBARPOS = 5,
                ABM_ACTIVATE = 6,
                ABM_GETAUTOHIDEBAR = 7,
                ABM_SETAUTOHIDEBAR = 8,
                ABM_WINDOWPOSCHANGED = 9,
                ABM_SETSTATE = 10
            }

            public enum ABNotify : int
            {
                ABN_STATECHANGE = 0,
                ABN_POSCHANGED,
                ABN_FULLSCREENAPP,
                ABN_WINDOWARRANGE
            }

            private enum ABEdge : int
            {
                ABE_LEFT = 0,
                ABE_TOP,
                ABE_RIGHT,
                ABE_BOTTOM
            }

            [DllImport("SHELL32", CallingConvention = CallingConvention.StdCall)]
            static extern uint SHAppBarMessage(int dwMessage, ref APPBARDATA pData);

            [DllImport("USER32")]
            static extern int GetSystemMetrics(int Index);

            [DllImport("User32.dll", ExactSpelling = true,
                CharSet = System.Runtime.InteropServices.CharSet.Auto)]
            static extern bool MoveWindow
                (IntPtr hWnd, int x, int y, int cx, int cy, bool repaint);

            [DllImport("User32.dll", CharSet = CharSet.Auto)]
            static extern int RegisterWindowMessage(string msg);

            public static void ABSetPos(IntPtr Handle, int Width, int Height)
            {
                APPBARDATA abd = new APPBARDATA();
                abd.cbSize = Marshal.SizeOf(abd);
                abd.hWnd = Handle;
                abd.uEdge = (int)ABEdge.ABE_RIGHT;

                if (abd.uEdge == (int)ABEdge.ABE_LEFT || abd.uEdge == (int)ABEdge.ABE_RIGHT)
                {
                    abd.rc.top = 0;
                    abd.rc.bottom = SystemInformation.PrimaryMonitorSize.Height;
                    if (abd.uEdge == (int)ABEdge.ABE_LEFT)
                    {
                        abd.rc.left = 0;
                        abd.rc.right = Width;
                    }
                    else
                    {
                        abd.rc.right = SystemInformation.PrimaryMonitorSize.Width;
                        abd.rc.left = abd.rc.right - Width;
                    }

                }
                else
                {
                    abd.rc.left = 0;
                    abd.rc.right = SystemInformation.PrimaryMonitorSize.Width;
                    if (abd.uEdge == (int)ABEdge.ABE_TOP)
                    {
                        abd.rc.top = 0;
                        abd.rc.bottom = Height;
                    }
                    else
                    {
                        abd.rc.bottom = SystemInformation.PrimaryMonitorSize.Height;
                        abd.rc.top = abd.rc.bottom - Height;
                    }
                }

                // Query the system for an approved size and position. 
                SHAppBarMessage((int)ABMsg.ABM_QUERYPOS, ref abd);

                // Adjust the rectangle, depending on the edge to which the 
                // appbar is anchored. 
                switch (abd.uEdge)
                {
                    case (int)ABEdge.ABE_LEFT:
                        abd.rc.right = abd.rc.left + Width;
                        break;
                    case (int)ABEdge.ABE_RIGHT:
                        abd.rc.left = abd.rc.right - Width;
                        break;
                    case (int)ABEdge.ABE_TOP:
                        abd.rc.bottom = abd.rc.top + Height;
                        break;
                    case (int)ABEdge.ABE_BOTTOM:
                        abd.rc.top = abd.rc.bottom - Height;
                        break;
                }

                // Pass the final bounding rectangle to the system. 
                SHAppBarMessage((int)ABMsg.ABM_SETPOS, ref abd);

                // Move and size the appbar so that it conforms to the 
                // bounding rectangle passed to the system. 
                MoveWindow(abd.hWnd, abd.rc.left, abd.rc.top,
                    abd.rc.right - abd.rc.left, abd.rc.bottom - abd.rc.top, true);
            }

            public static void RegisterBar(IntPtr Handle, int Width, int Height, ref bool fBarRegistered, ref int uCallBack)
            {
                APPBARDATA abd = new APPBARDATA();
                
                abd.cbSize = Marshal.SizeOf(abd);
                abd.hWnd = Handle;
                
                if (!fBarRegistered)
                {
                    uCallBack = RegisterWindowMessage("AppBarMessage");
                    abd.uCallbackMessage = uCallBack;

                    uint ret = SHAppBarMessage((int)ABMsg.ABM_NEW, ref abd);
                    
                    fBarRegistered = true;

                    ABSetPos(Handle, Width, Height);
                }
                else
                {
                    
                    SHAppBarMessage((int)ABMsg.ABM_REMOVE, ref abd);
                    fBarRegistered = false;
                }
            }
        }
        

    }
}
