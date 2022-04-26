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

        private static MainWindow ventanaPrincipal;
        public static MainWindow VentanaPrincipal
        {
            get { return ventanaPrincipal; }
            set { ventanaPrincipal = value; }
        }
        public MenuControl()
        {
            
            InitializeComponent();
            this.DataContext = VentanaPrincipal;
        }

        private void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            VentanaPrincipal.CrearItem_Click(sender, e);
        }
        private void ModificarItem_Click(object sender, RoutedEventArgs e)
        {
            VentanaPrincipal.ModificarItem_Click(sender, e);
        }
        private void BorrarItem_Click(object sender, RoutedEventArgs e)
        {
            VentanaPrincipal.BorrarItem_Click(sender, e);
        }

        private void VerEmpleados_Click(object sender, RoutedEventArgs e)
        {
            VentanaPrincipal.VerEmpleados_Click(sender, e);
        }

        private void VolverInicio_Click(object sender, RoutedEventArgs e)
        {
            VentanaPrincipal.VolverInicio_Click(sender, e);
        }
        private void Row_Click(object sender, RoutedEventArgs e)
        {
            VentanaPrincipal.IsEmpleadoSelected = true;
        }
    }
}
