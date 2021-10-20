package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_ACTOR;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.buyer.FindBuyerCommand;
import seedu.address.logic.commands.property.FindPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.field.Actor;
import seedu.address.model.field.ContainsTagsPredicate;
import seedu.address.model.field.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final int ACTOR_POSITIONAL_INDEX = 0;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        Actor actor = ParserUtil.parseActor(args, ACTOR_POSITIONAL_INDEX);

        // Replaces the actor with empty string
        String argsWithoutActor = args.trim().replaceFirst("^[a-z-]*", "");
        if (argsWithoutActor.isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsWithoutActor, PREFIX_TAG);
        List<String> nameKeywords = Arrays.stream(argMultimap.getPreamble().split("\\s+"))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        Set<Tag> tagsFilter = parseTags(argMultimap.getAllValues(PREFIX_TAG));

        switch (actor) {
        case PROPERTY:
            return new FindPropertyCommand(new NameContainsKeywordsPredicate<>(nameKeywords),
                    new ContainsTagsPredicate<>(tagsFilter));
        case BUYER:
            return new FindBuyerCommand(new NameContainsKeywordsPredicate<>(nameKeywords),
                    new ContainsTagsPredicate<>(tagsFilter));
        default:
            throw new ParseException(MESSAGE_INVALID_ACTOR);
        }

    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     */
    private Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Collections.emptySet();
        }
        return ParserUtil.parseTags(tags);
    }

}
