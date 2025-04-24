import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Title", "Due Date", "Completed"};
    private final ArrayList<Task> tasks;

    public TaskTableModel(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> task.getTitle();
            case 1 -> task.getDueDate();
            case 2 -> task.isCompleted();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            tasks.get(rowIndex).setCompleted((Boolean) value);
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case 0, 1 -> String.class;
            case 2 -> Boolean.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 2;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
