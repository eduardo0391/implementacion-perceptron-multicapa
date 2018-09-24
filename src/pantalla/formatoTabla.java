
package pantalla;

    import java.awt.Color;
    import java.awt.Component;
    import javax.swing.JTable;
    import javax.swing.table.DefaultTableCellRenderer;
    public class formatoTabla extends DefaultTableCellRenderer
    {
    @Override
    public Component getTableCellRendererComponent
    (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
        String valor1,valor2;
        valor1 =String.valueOf(table.getValueAt(row, 1));
        valor2= String.valueOf(table.getValueAt(row,2));
        //si los valores son diferentes 
       try
       {
        if ((valor1.charAt(2)== valor2.charAt(2))
       ||
       (Double.parseDouble(String.valueOf(valor1.charAt(2)))<6 && Double.parseDouble(String.valueOf(valor2.charAt(2)))<6 ))      
        this.setBackground(Color.white);
        else
                this.setBackground(Color.red);
        // SI NO ES ACTIVO ENTONCES COLOR ROJO
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        }
       catch(Exception ex)
       {
           System.out.print(ex.toString());
       }
    return this;
    }
    }