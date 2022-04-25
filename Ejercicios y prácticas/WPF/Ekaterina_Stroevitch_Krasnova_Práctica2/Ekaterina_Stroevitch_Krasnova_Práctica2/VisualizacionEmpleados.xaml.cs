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
        public VisualizacionEmpleados()
        {
            InitializeComponent();
            List<string> resps = new List<string>();
            resps.Add("Captación y mantenimiento de sponsors");
            resps.Add("Relación con usuarios");
            List<Empleado> lista = new List<Empleado>();
            lista.Add(new Empleado() { Nombre = "Juan", Apellidos="Palomo", Correo="jpalomo@gmail.com", 
                Departamento = "Servicios Compartidos", Puesto="Jefe", Posicion= "Director", Responsabilidades=resps, FechaContratacion="06/04/2022", Ciudad="Madrid"});
            dgEmpleados.ItemsSource = lista;
        }
    }
}
