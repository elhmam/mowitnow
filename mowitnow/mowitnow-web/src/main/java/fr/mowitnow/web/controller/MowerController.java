package fr.mowitnow.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.behvior.MowerBehaviorImpl;
import fr.mowitnow.core.exception.MowerParserException;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controleur affichant les déplacement des tondeuses.
 * @author elhmam
 */
@Controller
@RequestMapping(value = "/")
public class MowerController {    
  /**
   * LOGGER.
   */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(MowerController.class);
  /**
   * Tondeuse.
   */
  private static final String TONDEUSE = "Tondeuse ";
  /**
   * Pattern des lignes des instructions.
   */
  private static final String DELIMITER = "\\s*\n\\s*";
  /**
   * Espace.
   */
  private static final String BLANK = " ";
  /**
   * Limite verticale du champ.
   */
  private static final String YLIMIT = "ylimit";
  /**
   * Limite horizontale du champ.
   */
  private static final String XLIMIT = "xlimit";
  /**
   * Message d'erreur.
   */
  private static final String ERROR = "error";
  /**
   * Sert à afficher la trajectoire de la tondeuse par étape Map <id du tondeuse
   * - liste tondeuses>.
   */
  private static final String MOWERS_MAP = "mowersMap";
  /**
   * Liste des instructions.
   */
  private static final String INSTRUCTIONS = "instructions";
  /**
   * Page du formulaire.
   */
  private static final String HOME = "home";
  /**
   * Liste des instructions par défaut.
   */
  private static final StringBuilder INSTRUCTIONS_LST = new StringBuilder()
      .append("5 5\n").append("1 2 N\n").append("GAGAGAGAA\n")
      .append("3 3 E\n").append("AADAADADDA\n");

  /**
   * MowerParser.
   */
  private MowerParser mowerParser = new MowerParserImpl();
  /**
   * MowerBehavior.
   */
  MowerBehavior mowerBehavior = new MowerBehaviorImpl();

  /**
   * Affichage du formulaire.
   * @param model
   *          : Model
   * @return La page à afficher
   */
  @RequestMapping(method = RequestMethod.GET)
  public String mowitnowForm(Model model) {
    model.addAttribute(INSTRUCTIONS, INSTRUCTIONS_LST.toString());
    return HOME;
  }

  /**
   * Traitement du formulaire.
   * @param instructions : Instructions renseignées
   * @param model : Model
   * @return La page à afficher
   */
  @RequestMapping(method = RequestMethod.POST)
  public String mowitnowSubmit(
      @ModelAttribute(INSTRUCTIONS) String instructions, Model model) {

    List<String> lines = getListFromInstructionString(instructions);

    // création des tondeuses
    List<Mower> mowers = null;
    try {
      mowers = mowerParser.loadMowers(lines);
    } catch (MowerParserException e) {
      LOGGER.error(e.getMessage(), e);
      model.addAttribute(ERROR, e.getMessage());
    }

    if (mowers != null) {
      // map des déplacements
      Map<Integer, List<Mower>> mowersMap = new HashMap<>();     
      for (int j = 0; j < mowers.size(); j++) {
        List<Mower> steps = new ArrayList<>();
        Mower mower = new Mower(mowers.get(j).getX(), mowers.get(j).getY(),
            mowers.get(j).getOrientation(), mowers.get(j).getLawn(), mowers
                .get(j).getPath());
        LOGGER.info(TONDEUSE + (j + 1) + " > " + mower.getX() + ","
            + mower.getY() + "," + mower.getOrientation());
        steps.add(mower);
        for (char c : mowers.get(j).getPath().toCharArray()) {
          mowerBehavior.execute(mowers.get(j), c);
          Mower mower1 = new Mower(mowers.get(j).getX(), mowers.get(j).getY(),
              mowers.get(j).getOrientation(), mowers.get(j).getLawn(), mowers
                  .get(j).getPath());
          LOGGER.info(TONDEUSE + (j + 1) + " > " + mower1.getX() + ","
              + mower1.getY() + "," + mower1.getOrientation());
          steps.add(mower1);
        }
        mowersMap.put(j, steps);
      }
      model.addAttribute(MOWERS_MAP, mowersMap);
    }

    model.addAttribute(INSTRUCTIONS, instructions.toString());
    model.addAttribute(XLIMIT, lines.get(0).split(BLANK)[0]);
    model.addAttribute(YLIMIT, lines.get(0).split(BLANK)[1]);

    return HOME;
  }
  /**
   * Retourne la liste des instructions
   * @param instructions : Chaine de caractères
   * @return La liste des insctructions
   */
  public List<String> getListFromInstructionString(String instructions) {
    List<String> lines = new ArrayList<>();
    // lire les instructions
    @SuppressWarnings("resource")
    Scanner s = new Scanner(instructions).useDelimiter(DELIMITER);
    while (s.hasNext()) {
      lines.add(s.nextLine());
    }
    return lines;
  }

}
