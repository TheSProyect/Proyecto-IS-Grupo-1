package main.views.components;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.utils.Palette;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewDomainsPanel extends JPanel implements ActionListener {
    List<NewDomainTextField> domains;
    JPanel domainsPanel;
    JButton addButton;

    NewDomainsPanel() {
        domains = new ArrayList<NewDomainTextField>();
        buildPanel(this);
        paintDomainsPanel();
        paintOptionsList();
        paintAddButtonPanel();
    }

    private void buildPanel(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Palette.instance().getWhite());
    }

    private void paintDomainsPanel() {
        domainsPanel = new JPanel();
        buildPanel(domainsPanel);

        domains.add(new NewDomainTextField());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(domainsPanel, constraints);
    }

    private void paintOptionsList() {
        domainsPanel.removeAll();
        for (int i = 0; i < domains.size(); i++) {
            domainsPanel.add(domains.get(i), createNewOptionsConstraints(i));
        }
        addActionListenerDeleteButtons(this);
    }

    public void addActionListenerDeleteButtons(ActionListener listener) {
        for(NewDomainTextField domain : domains) {
            if(!containsActionListener(listener, domain)) {
                domain.getDeleteButton().addActionListener(listener);
            }
        }
    }

    private boolean containsActionListener(ActionListener listener, NewDomainTextField domain) {
        for (ActionListener actionListener : domain.getDeleteButton().getActionListeners()) {
            if (actionListener == listener) {
                return true;
            }
        }
        return false;
    }

    private GridBagConstraints createNewOptionsConstraints(int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = gridy;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(5, 0, 5, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        return constraints;
    }

    private void paintAddButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonPanel.setBackground(Palette.instance().getWhite());

        paintAddButton();
        paintAddButtonIcon();

        buttonPanel.add(addButton);

        this.add(buttonPanel, createAddButtonConstraints());
        
        buttonPanel.setMaximumSize(new Dimension(buttonPanel.getWidth(), 50));
    }

    private void paintAddButton() {
        addButton = new JButton();
        addButton.setBackground(Palette.instance().getYellow());
        addButton.setPreferredSize(new Dimension(40,40));
        addButton.setMinimumSize(new Dimension(40,40));
        addButton.addActionListener(this);
        addButton.setFocusable(false);
        
        Border border = BorderFactory.createLineBorder(Palette.instance().getYellow());
        addButton.setBorder(border);
    }

    private void paintAddButtonIcon() {
        ImageIcon icon = new ImageIcon("src/assets/Plus_Icon.png");
        addButton.setIconTextGap(15);

        addButton.setIcon(icon);
    }

    private GridBagConstraints createAddButtonConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        return constraints;
    }

    public void actionEventInAddButton(ActionEvent e) {
        if (e.getSource() == addButton) {
            domains.add(new NewDomainTextField());
            paintOptionsList();
            this.validate();
            this.repaint();
        }
    }

    public void actionEventInDeleteButton(ActionEvent e) {
        if (domains.size() <= 1) {
            return;
        }
        for(int i = 0; i < domains.size(); i++){
            if(e.getSource() == domains.get(i).getDeleteButton()) {
                domainsPanel.remove(domains.get(i));
                this.revalidate();

                domains.remove(i);

                paintOptionsList();
                this.validate();
                this.repaint();
            }
        }
    }

    public boolean checkDomainsAreBlank() {
        for (NewDomainTextField domain: domains){
            if (domain.getDomainText().isBlank()) {
                return true;
            }
        }
        return false;
    }

    public List<String> getDomainsText() {
        List<String> domainsText = new ArrayList<String>();
        for (NewDomainTextField domain: domains){
            domainsText.add(domain.getDomainText());
        }
        return domainsText;
    }

    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
