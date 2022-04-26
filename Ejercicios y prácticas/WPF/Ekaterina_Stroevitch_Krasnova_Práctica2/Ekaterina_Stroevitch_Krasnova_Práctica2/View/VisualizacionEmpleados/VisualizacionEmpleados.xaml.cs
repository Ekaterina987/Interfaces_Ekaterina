using Ekaterina_Stroevitch_Krasnova_Práctica2.UserControls;
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

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    /// <summary>
    /// Lógica de interacción para VisualizacionEmpleados.xaml
    /// </summary>
    public partial class VisualizacionEmpleados : Page
    {
        private static MainWindow ventanaPrincipal;
        public static MainWindow VentanaPrincipal
        {
            get { return ventanaPrincipal; }
            set { ventanaPrincipal = value; }
        }
        public VisualizacionEmpleados()
        {
            InitializeComponent();
            VentanaPrincipal.IsEmpleadoSelected = false;
            this.DataContext = VentanaPrincipal;
            dgEmpleados.DataContext = VentanaPrincipal;
            generarTabla();
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
        private void Row_Click(object sender, RoutedEventArgs e)
        {
            Empleado empleado = (Empleado)dgEmpleados.SelectedItem;
            VentanaPrincipal.EmpleadoActual = empleado;
            VentanaPrincipal.IsEmpleadoSelected = true;
        }
        public void generarTabla()
        {
            dgEmpleados.ItemsSource = VentanaPrincipal.EmpleadosList;
        }
    }
}
