using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2.UserControls
{
    /// <summary>
    /// Lógica de interacción para MenuControl.xaml
    /// </summary>
    public partial class MenuControl : UserControl
    {
        private static Boolean isEmpleadoSelected;
        public static Boolean IsEmpleadoSelected
        {
            get { return isEmpleadoSelected; }
            set { isEmpleadoSelected = value; }
        }

        public MenuControl()
        {
            IsEmpleadoSelected = true;
            InitializeComponent();
           
        }

        private void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.CrearItem_Click(sender, e);
        }
        private void ModificarItem_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.ModificarItem_Click(sender, e);
        }

        private void VerEmpleados_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.VerEmpleados_Click(sender, e);
        }

        private void VolverInicio_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.VolverInicio_Click(sender, e);
        }
    }
}
