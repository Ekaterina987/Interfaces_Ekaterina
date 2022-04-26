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
        public Boolean IsEmpleadoSelected
        {
            get { return (Boolean)GetValue(IsEmpleadoSelectedProperty); }
            set { SetValue(IsEmpleadoSelectedProperty, value); }
        }

        public static readonly DependencyProperty IsEmpleadoSelectedProperty =
            DependencyProperty.Register("IsEmpleadoSelected", typeof(Boolean), typeof(VisualizacionEmpleados), new PropertyMetadata(default(Boolean)));

        public VisualizacionEmpleados()
        {
            this.DataContext = this;
            IsEmpleadoSelected = false;
            InitializeComponent();

            dgEmpleados.ItemsSource = MainWindow.EmpleadoList;
        }
        private void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            MainWindow.CrearItem_Click(sender, e);
        }
        private void ModificarItem_Click(object sender, RoutedEventArgs e)
        {
            Empleado empleado = (Empleado)dgEmpleados.SelectedItem;
            MainWindow.EmpleadoActual = empleado;
            MainWindow.ModificarItem_Click(sender, e);
        }
        private void Row_Click(object sender, RoutedEventArgs e)
        {
            IsEmpleadoSelected=true;
            MenuControl.IsEmpleadoSelected = true;
            
        }
    }
}
