using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Common
{
    /// <summary>
    /// 
    /// </summary>
    public interface ICaptionPanel
    {
        /// <summary>
        /// 
        /// </summary>
        System.Windows.Forms.Padding CaptionPadding { get; }

        /// <summary>
        /// 
        /// </summary>
        bool FolderOpened { get; }

        /// <summary>
        /// 
        /// </summary>
        bool AddControlOrder { get; }
    }
}
