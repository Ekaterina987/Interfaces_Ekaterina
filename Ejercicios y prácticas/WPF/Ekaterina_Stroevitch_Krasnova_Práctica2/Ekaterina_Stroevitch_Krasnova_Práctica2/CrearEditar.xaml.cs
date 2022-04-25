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
    /// Lógica de interacción para CrearEditar.xaml
    /// </summary>
    public partial class CrearEditar : Page
    {
        public CrearEditar()
        {
            InitializeComponent();
        }

        private void Button_Crear(object sender, RoutedEventArgs e)
        {
            ComboBoxItem cbiD = (ComboBoxItem)ComboDepartamento.SelectedItem;
            ComboBoxItem cbiC = (ComboBoxItem)ComboCiudad.SelectedItem;
            System.Collections.IList items = (System.Collections.IList)ListaResponsabilidades.SelectedItems;
            var collection = items.Cast<string>();

            Empleado = new Empleado() { Nombre = Nombre.Text, Apellidos = Apellidos.Text, 
                Correo = Correo.Text, Responsabilidades = items, Contrasenia = Contrasenia.Password.ToString(), 
                Departamento = cbiD.Content.ToString(), Posicion = this.Pos, Puesto = Puesto.Text, 
                FechaContratacion = Date.SelectedDate.Value.ToString(), Ciudad = cbiC.Content.ToString()};

            MessageBox.Show(Empleado.Nombre + " " + Empleado.Apellidos + " " + Empleado.Correo + " " + Empleado.Contrasenia + 
                " " + Empleado.Departamento + " " + Empleado.Posicion + " " + Empleado.Puesto + " " + Empleado.FechaContratacion +
                " " + Empleado.Ciudad);

        }
        internal string Pos;
        public Empleado Empleado;

        private void RadioEmpleado_Click(object sender, RoutedEventArgs e)
        {
            Pos = "Empleado";
        }

        private void RadioAdmin_Click(object sender, RoutedEventArgs e)
        {
            Pos = "Admin";
        }

        private void RadioDirector_Click(object sender, RoutedEventArgs e)
        {
            Pos = "Director";
        }
    }
}
