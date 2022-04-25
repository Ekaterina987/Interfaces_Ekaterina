﻿using System;
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

            dgEmpleados.ItemsSource = MainWindow.EmpleadoList;
        }

        private void ModificarItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar.Editar = true;
            CrearEditar ce = new CrearEditar();
            

            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ce;
        }
    }
}
