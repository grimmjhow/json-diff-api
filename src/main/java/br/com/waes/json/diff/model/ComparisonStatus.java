package br.com.waes.json.diff.model;

/**
 * Represent all states when comparing one Json struct to another.
 * <ul>
 *     <li>NONE -> It's a default value when none of the valid options was chose</li>
 *     <li>EQUAL -> When a json struct have the same size and content</li>
 *     <li>DIFFERENT_SIZE -> When a json struct haven't the same size of attributes</li>
 *     <li>DIFFERENT_CONTENT -> When a json struct have the same size of attributes, but not have same content</li>
 * </ul>
 */
public enum ComparisonStatus {
    NONE, EQUAL, DIFFERENT, DIFFERENT_CONTENT, DIFFERENT_SIZE
}
